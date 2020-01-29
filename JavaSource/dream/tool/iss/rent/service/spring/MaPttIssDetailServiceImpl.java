package dream.tool.iss.rent.service.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.iss.rent.dao.MaPttIssDetailDAO;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;
import dream.tool.iss.rent.service.MaPttIssDetailService;

/**
 * 구매입고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttIssDetailServiceTarget"
 * @spring.txbn id="maPttIssDetailService"
 * @spring.property name="maPttIssDetailDAO" ref="maPttIssDetailDAO"
 */
public class MaPttIssDetailServiceImpl implements MaPttIssDetailService
{
    private MaPttIssDetailDAO maPttIssDetailDAO = null;
    
    public MaPttIssDetailDAO getMaPttIssDetailDAO() 
    {
		return maPttIssDetailDAO;
	}

	public void setMaPttIssDetailDAO(MaPttIssDetailDAO maPttIssDetailDAO) 
	{
		this.maPttIssDetailDAO = maPttIssDetailDAO;
	}

	public MaPttIssDetailDTO findDetail(User user, String ptIssListId)throws Exception
    {
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailDAO.findDetail(user, ptIssListId);
        
        return maPttIssDetailDTO;
    }
	
	public int insertDetail(MaPttIssDetailDTO maPttIssDetailDTO) throws Exception
    {   
        return maPttIssDetailDAO.insertDetail(maPttIssDetailDTO);
    }
	
	public int updateDetail(MaPttIssDetailDTO maPttIssDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = maPttIssDetailDTO.getCompNo();
	    String ptIssListId = maPttIssDetailDTO.getPtIssListId();
	    
	    // 상태가 입고완료[C]가 아닌 경우만 Update 한다. 
        String preRecListStatus = maPttIssDetailDAO.findPtIssListStatus(compNo, ptIssListId);
        if(!"C".equals(preRecListStatus))
        {
	        resultCnt = maPttIssDetailDAO.updateDetail(maPttIssDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] issuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException
    {
        /** @param bwart 이동유형 201
         * @param budat 전기일 20160726
         * @param werks 플랜트 S111
         * @param lgort 저장위치 1190
         * @param gsber 사업영역 S110
         * @param kostl 코스트센터 현업문의-->  S21032
         * @param matnr 품번 BDMZ-00061
         * @param menge 수량 5
         * @param meins 수량단위 EA
         */
        String rtnValue = "S";
        String[] rtnArr = new String[4];

        //Part Grade :B 면 ERP 호출 없이 출고 진행

        maPttIssDetailDTO.setPtissStatus("C");
        maPttIssDetailDAO.updatePtIssList(maPttIssDetailDTO);

        String ptisshistId = maPttIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPttIssDetailDAO.insertPtIssHist(maPttIssDetailDTO, ptisshistId, "C");

        //SP_PT_OUTSTOCK 스케쥴러 실행
        maPttIssDetailDAO.execSP_PT_OUTSTOCK(maPttIssDetailDTO, ptisshistId);
        
        //TAPTRENT_STOCK 수정
        maPttIssDetailDAO.insertPtRentStock(maPttIssDetailDTO);
        rtnArr[0] = "S";
       

        return rtnArr;
    }
    
	public String[] cancelIssuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException
    {               
        String rtnValue = "S";
        String[] rtnArr = new String[4];

 
            maPttIssDetailDTO.setPtissStatus("X"); //취소
            
            String ptisshistId = maPttIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
            maPttIssDetailDAO.insertPtIssHist(maPttIssDetailDTO, ptisshistId, "R");

            //원래의 PTISSLIST를 업데이트? 
            maPttIssDetailDAO.updateDetail(maPttIssDetailDTO);
            
            //SP_PT_OUTSTOCK 스케쥴러 실행
            maPttIssDetailDAO.execSP_PT_OUTSTOCK(maPttIssDetailDTO, ptisshistId);
            
            //TAPTRENT_STOCK 수정
            maPttIssDetailDAO.updatePtRentStock(maPttIssDetailDTO);
            rtnArr[0] = "S";
        
        
        return rtnArr;
    }
}
