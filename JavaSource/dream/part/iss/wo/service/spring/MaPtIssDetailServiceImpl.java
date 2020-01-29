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
 * �������Ȯ�� - �� serviceimpl 
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
	       
	       //1. ����Ͻ� �۾��� �����ߴ��� Ȯ���ؾ� ��. ���࿡ �۾��� �����ߴٸ� �� �۾����� �Ϸ�Ǿ����� Ȯ���ϰ� �Ϸᰡ �ƴѰ�쿡��  ����ǰ�� �߰���.
	       if(!"".equals(maPtIssDetailDTO.getWkorId())){
	          //�̰�� �۾��� Ȯ���ϰ� �۾��� �Ϸᰡ �ƴ϶�� ����ǰ��  �߰��ϰ� ����� ������.
	          isExists = maPtIssDetailDAO.checkWorkOrderStatus(maPtIssDetailDTO, user);
	          if(isExists){
	             // work order ����ǰ�� �ش� ��ǰ�� �߰���
	             maPtIssDetailDTO.setWopartId(maPtIssDetailDAO.getNextSequence("SQAWOPART_ID"));
	             maPtIssDetailDAO.insertWoPart(maPtIssDetailDTO, user);
	             maPtIssDetailDTO.setPtissType("WOISS");  //������� 
	          }else{
	             maPtIssDetailDTO.setWkorId("");
	          }
	       }
	       //2.����� ����ǰ�� �߰�.
	       result = maPtIssDetailDAO.insertPtIssList(maPtIssDetailDTO, user);
	        return result;
	    }
	   
	   
	   public int updateDetail(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
	    {   
	       int result = 0;
	       boolean isExists = false;
	       
	       //1.work order�� ��ǰ ���key�� ���ٰ� ����ڰ� wo��ȣ�� �Է��ߴٸ� üũ�ؼ� �űԷ� ����ǰ�� �߰��ؾ� ��.
	       if("".equals(maPtIssDetailDTO.getWopartId())){
	          if(!"".equals(maPtIssDetailDTO.getWkorId())){
	             //�̰�� �۾��� Ȯ���ϰ� �۾��� �Ϸᰡ �ƴ϶�� ����ǰ��  �߰��ϰ� ����� ������.
	             isExists = maPtIssDetailDAO.checkWorkOrderStatus(maPtIssDetailDTO, user);
	             if(isExists){
	                // work order ����ǰ�� �ش� ��ǰ�� �߰���
	                maPtIssDetailDTO.setWopartId(maPtIssDetailDAO.getNextSequence("SQAWOPART_ID"));
	                maPtIssDetailDAO.insertWoPart(maPtIssDetailDTO, user);
	                maPtIssDetailDTO.setPtissType("WOISS");  //������� 
	             }else{
	                maPtIssDetailDTO.setWkorId("");
	             }
	          }
	       }else{
	          result=+ maPtIssDetailDAO.updateWoPart(maPtIssDetailDTO, user);
	          maPtIssDetailDTO.setPtissType("WOISS");  //������� 
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
        //����̷¿� ����Ÿ�� �Է�
        String ptisshistId = maPtIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPtIssDetailDAO.insertPtIssHist(maPtIssDetailDTO, ptisshistId, "C", user);
        //��� �����ϴ� ���ν��� ����
        maPtIssDetailDAO.execSP_PT_OUTSTOCK(ptisshistId, user);
        
        /***********************************************************************************************/
        //�߰� Ȯ���� �ʿ��� ��� ���⿡ ����............................................................................
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
        
        
        maPtIssDetailDTO.setPtissStatus("X");  //������
        maPtIssDetailDAO.confirmIssuePart(maPtIssDetailDTO, user);
        //����̷¿� ����Ÿ�� �Է�
        String ptisshistId = maPtIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPtIssDetailDAO.insertPtIssHist(maPtIssDetailDTO, ptisshistId, "R", user);
        //��� �����ϴ� ���ν��� ����
        maPtIssDetailDAO.execSP_PT_OUTSTOCK(ptisshistId, user);
        
        /***********************************************************************************************/
        //�߰� Ȯ���� �ʿ��� ��� ���⿡ ����............................................................................
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

