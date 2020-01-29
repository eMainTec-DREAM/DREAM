package dream.part.iss.wo.service.spring;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import common.config.service.ResourceService;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.part.iss.wo.dao.MaPtIssDetailDAO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.service.MaPtIssDetailService;

/**
 * 자재출고확정 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtIssDetailServiceTarget"
 * @spring.txbn id="maPtIssDetailService"
 * @spring.property name="maPtIssDetailDAO" ref="maPtIssDetailDAO"
 */
public class MaPtIssDetailServiceImpl implements MaPtIssDetailService
{
    private MaPtIssDetailDAO maPtIssDetailDAO = null;
    
    public MaPtIssDetailDAO getMaPtIssDetailDAO() 
    {
		return maPtIssDetailDAO;
	}

	public void setMaPtIssDetailDAO(MaPtIssDetailDAO maPtIssDetailDAO) 
	{
		this.maPtIssDetailDAO = maPtIssDetailDAO;
	}

	public MaPtIssDetailDTO findDetail(MaPtIssCommonDTO maPtIssCommonDTO, User user)throws Exception
    {
        return maPtIssDetailDAO.findDetail(maPtIssCommonDTO,user);
    }

	
	  public int insertDetail(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
	    {   
	       int result = 0;
	       boolean isExists = false;
	       
	       //1. 출고등록시 작업을 선택했는지 확인해야 함. 만약에 작업을 선택했다면 그 작업서가 완료되었는지 확인하고 완료가 아닌경우에만  사용부품에 추가함.
	       if(!"".equals(maPtIssDetailDTO.getWkorId())){
	          //이경우 작업을 확인하고 작업이 완료가 아니라면 사용부품에  추가하고 출고서를 저장함.
	          isExists = maPtIssDetailDAO.checkWorkOrderStatus(maPtIssDetailDTO, user);
	          if(isExists){
	             // work order 사용부품에 해당 부품을 추가함
	             maPtIssDetailDTO.setWopartId(maPtIssDetailDAO.getNextSequence("SQAWOPART_ID"));
	             maPtIssDetailDAO.insertWoPart(maPtIssDetailDTO, user);
	             maPtIssDetailDTO.setPtissType("WOISS");  //오더출고 
	          }else{
	             maPtIssDetailDTO.setWkorId("");
	          }
	       }
	       //2.출고서에 출고부품을 추가.
	       result = maPtIssDetailDAO.insertPtIssList(maPtIssDetailDTO, user);
	        return result;
	    }
	   
	   
	   public int updateDetail(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
	    {   
	       int result = 0;
	       boolean isExists = false;
	       
	       //1.work order에 부품 사용key가 없다가 사용자가 wo번호를 입력했다면 체크해서 신규로 사용부품을 추가해야 함.
	       if("".equals(maPtIssDetailDTO.getWopartId())){
	          if(!"".equals(maPtIssDetailDTO.getWkorId())){
	             //이경우 작업을 확인하고 작업이 완료가 아니라면 사용부품에  추가하고 출고서를 저장함.
	             isExists = maPtIssDetailDAO.checkWorkOrderStatus(maPtIssDetailDTO, user);
	             if(isExists){
	                // work order 사용부품에 해당 부품을 추가함
	                maPtIssDetailDTO.setWopartId(maPtIssDetailDAO.getNextSequence("SQAWOPART_ID"));
	                maPtIssDetailDAO.insertWoPart(maPtIssDetailDTO, user);
	                maPtIssDetailDTO.setPtissType("WOISS");  //오더출고 
	             }else{
	                maPtIssDetailDTO.setWkorId("");
	             }
	          }
	       }else{
	          result=+ maPtIssDetailDAO.updateWoPart(maPtIssDetailDTO, user);
	          maPtIssDetailDTO.setPtissType("WOISS");  //오더출고 
	       }
	       
	       result=+ maPtIssDetailDAO.updatePtIssList(maPtIssDetailDTO, user);
	       
	       return result;
	    }
	   

	
    @Override
    public String[] confirmIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
    {
        String[] rtnArr = new String[2];
        
        String useQtyStr = maPtIssDetailDAO.getUseQty(maPtIssDetailDTO, user);
        double useQty = Double.parseDouble("".equals(useQtyStr)?"0":useQtyStr);
        double issueQty = Double.parseDouble(maPtIssDetailDTO.getIssueQty());
        
        if(useQty>issueQty){
            ResourceService resourceService = (ResourceService) CommonUtil.getBean("resourceService");
            rtnArr[0] = "E";
            rtnArr[1] = resourceService.getMessage(user.getLangId(), user.getLangId()+".MESSAGE.MSG0247");
            return rtnArr;
        }
        
        maPtIssDetailDTO.setPtissStatus("C");
        maPtIssDetailDAO.confirmIssuePart(maPtIssDetailDTO, user);
        //출고이력에 데이타를 입력
        String ptisshistId = maPtIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPtIssDetailDAO.insertPtIssHist(maPtIssDetailDTO, ptisshistId, "C", user);
        //재고 조정하는 프로시저 실행
        maPtIssDetailDAO.execSP_PT_OUTSTOCK(ptisshistId, user);
        
        /***********************************************************************************************/
        //추가 확장이 필요한 경우 여기에 구현............................................................................
        /***********************************************************************************************/
        maPtIssDetailDAO.updateEqPart(maPtIssDetailDTO, user);

        
        rtnArr[0] = "S";
        
        MailUtil.sendMail("ISS10", maPtIssDetailDTO.getPtisslistId(), user);
        
        return rtnArr;
    }
    

    @Override
    public String[] cancelIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws RemoteException, ServiceException
    {               
        String[] rtnArr = new String[4];
        
        
        maPtIssDetailDTO.setPtissStatus("X");  //출고취소
        maPtIssDetailDAO.confirmIssuePart(maPtIssDetailDTO, user);
        //출고이력에 데이타를 입력
        String ptisshistId = maPtIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPtIssDetailDAO.insertPtIssHist(maPtIssDetailDTO, ptisshistId, "R", user);
        //재고 조정하는 프로시저 실행
        maPtIssDetailDAO.execSP_PT_OUTSTOCK(ptisshistId, user);
        
        /***********************************************************************************************/
        //추가 확장이 필요한 경우 여기에 구현............................................................................
        /***********************************************************************************************/
        maPtIssDetailDAO.updateCancelEqPart(maPtIssDetailDTO, user);
        
        
        
        rtnArr[0] = "S";
        
        
        return rtnArr;
    }

    @Override
    public String findStockQty(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
    {
        return maPtIssDetailDAO.findStockQty(maPtIssDetailDTO, user);
    }
    
    
}

