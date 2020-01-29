package dream.tool.iss.rtn.service.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.iss.rtn.dao.MaPttRtnDetailDAO;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;
import dream.tool.iss.rtn.service.MaPttRtnDetailService;

/**
 * 공기구반납 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttRtnDetailServiceTarget"
 * @spring.txbn id="maPttRtnDetailService"
 * @spring.property name="maPttRtnDetailDAO" ref="maPttRtnDetailDAO"
 */
public class MaPttRtnDetailServiceImpl implements MaPttRtnDetailService
{
    private MaPttRtnDetailDAO maPttRtnDetailDAO = null;
    
    public MaPttRtnDetailDAO getMaPttRtnDetailDAO() 
    {
		return maPttRtnDetailDAO;
	}

	public void setMaPttRtnDetailDAO(MaPttRtnDetailDAO maPttRtnDetailDAO) 
	{
		this.maPttRtnDetailDAO = maPttRtnDetailDAO;
	}

	public MaPttRtnDetailDTO findDetail(User user, String ptRtnListId)throws Exception
    {
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailDAO.findDetail(user, ptRtnListId);
        
        return maPttRtnDetailDTO;
    }
	
	public int insertDetail(MaPttRtnDetailDTO maPttRtnDetailDTO) throws Exception
    {   
        return maPttRtnDetailDAO.insertDetail(maPttRtnDetailDTO);
    }
	
	public int updateDetail(MaPttRtnDetailDTO maPttRtnDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = maPttRtnDetailDTO.getCompNo();
	    String ptRtnListId = maPttRtnDetailDTO.getPtRtnListId();
	    
	    // 상태가 반납완료[C]가 아닌 경우만 Update 한다. 
        String ptRtnListStatus = maPttRtnDetailDAO.findPtRtnListStatus(compNo, ptRtnListId);
        if(!"C".equals(ptRtnListStatus))
        {
	        resultCnt = maPttRtnDetailDAO.updateDetail(maPttRtnDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] rtnPart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException
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

        maPttRtnDetailDTO.setPtRtnStatus("C");
        maPttRtnDetailDAO.updatePtRtnList(maPttRtnDetailDTO);

        //String ptisshistId = maPttRtnDetailDAO.getNextSequence("SQAPTRTNHIST_ID");
        //maPttRtnDetailDAO.insertPtIssHist(maPttRtnDetailDTO, ptisshistId, "C");

        //재고수량조정
        maPttRtnDetailDAO.updatePtStock(maPttRtnDetailDTO);
        //TAPTRENT_STOCK 수정
        maPttRtnDetailDAO.insertPtRentStock(maPttRtnDetailDTO);
        rtnArr[0] = "S";
       

        return rtnArr;
    }
    
	public String[] cancelIssuePart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException
    {               
        String rtnValue = "S";
        String[] rtnArr = new String[4];

 
            maPttRtnDetailDTO.setPtRtnStatus("X"); //취소
            
            String ptisshistId = maPttRtnDetailDAO.getNextSequence("SQAPTRTNHIST_ID");
            maPttRtnDetailDAO.insertPtIssHist(maPttRtnDetailDTO, ptisshistId, "R");

            //원래의 PTRTNLIST를 업데이트? 
            maPttRtnDetailDAO.updateDetail(maPttRtnDetailDTO);
            
            //재고수량조정
            maPttRtnDetailDAO.cancelPtStock(maPttRtnDetailDTO);
            
            //TAPTRENT_STOCK 수정
            maPttRtnDetailDAO.cancelPtRentStock(maPttRtnDetailDTO);
            rtnArr[0] = "S";
        
            
        return rtnArr;
    }
}
