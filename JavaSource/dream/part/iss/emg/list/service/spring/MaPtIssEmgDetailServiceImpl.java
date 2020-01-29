package dream.part.iss.emg.list.service.spring;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dao.MaPtIssEmgDetailDAO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;

/**
 * 긴급출고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtIssEmgDetailServiceTarget"
 * @spring.txbn id="maPtIssEmgDetailService"
 * @spring.property name="maPtIssEmgDetailDAO" ref="maPtIssEmgDetailDAO"
 */
public class MaPtIssEmgDetailServiceImpl implements MaPtIssEmgDetailService
{
    private MaPtIssEmgDetailDAO maPtIssEmgDetailDAO = null;

    public MaPtIssEmgDetailDAO getMaPtIssEmgDetailDAO() 
    {
		return maPtIssEmgDetailDAO;
	}

	public void setMaPtIssEmgDetailDAO(MaPtIssEmgDetailDAO maPtIssEmgDetailDAO) 
	{
		this.maPtIssEmgDetailDAO = maPtIssEmgDetailDAO;
	}

	public MaPtIssEmgDetailDTO findDetail(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)throws Exception
    {
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailDAO.findDetail(maPtIssEmgCommonDTO, user);
        
        return maPtIssEmgDetailDTO;
    }
	
	public int insertDetail(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
	    //TAPTISSREQ 입력
	    String ptemgissreqId = maPtIssEmgDetailDAO.getNextSequence("SQAPTEMGISSREQ_ID");
	    maPtIssEmgDetailDTO.setPtemgissreqId(ptemgissreqId);
	    maPtIssEmgDetailDAO.insertPtIssEmgReq(maPtIssEmgDetailDTO, loginUser);
	    //TAPTISSLIST 입력
	    result = maPtIssEmgDetailDAO.insertPtIssEmgList(maPtIssEmgDetailDTO, loginUser);
	    
        return result;
    }
	
	public int updateDetail(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
	    //TAPTISSLIST 수정
	    result=+ maPtIssEmgDetailDAO.updatePtIssEmgList(maPtIssEmgDetailDTO);

	    return result;
    }

	public String[] issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException {
		
		//출고창고가 있으면, 출고처리
		if(!"".equals(maPtIssEmgDetailDTO.getWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTISSHIST_ID");
			String issMode = "C"; // 출고
			String issType = "MOVE"; // 일반출고
			String wcodeId = maPtIssEmgDetailDTO.getWcodeId();  //출고창고
			maPtIssEmgDetailDAO.insertPtIssHist(maPtIssEmgDetailDTO, histId, issMode,issType,wcodeId, user); // 출고 이력 남기기
			maPtIssEmgDetailDAO.execPtIss(histId, user); //출고 실행
		}
		//보관창고가 있으면, 입고처리
		if(!"".equals(maPtIssEmgDetailDTO.getToWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTRECHIST_ID");
			String recMode = "C"; 	// 입고
			String recType = "MOVE"; 
			String wcodeId = maPtIssEmgDetailDTO.getToWcodeId();  //입고창고
			maPtIssEmgDetailDAO.insertPtRecHist(maPtIssEmgDetailDTO, histId, recMode, recType,wcodeId, user);
			maPtIssEmgDetailDAO.execPtRec(histId, user);
		}
		maPtIssEmgDetailDAO.issueComp(maPtIssEmgDetailDTO,user); // 상태 , 출고일자, 출고부서, 출고자(TAEMP) UPDATE
		maPtIssEmgDetailDAO.completePtIssEmgReqStatus(maPtIssEmgDetailDTO, user);
		
		return new String[]{"S",""};
	}
	public String[] issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException {
		
		//출고창고가 있으면, 출고취소
		if(!"".equals(maPtIssEmgDetailDTO.getWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTISSHIST_ID");
			String issMode = "R"; // 출고취소
			String issType = "MOVE"; // 이동출고
			String wcodeId = maPtIssEmgDetailDTO.getWcodeId();  //출고창고
			maPtIssEmgDetailDAO.insertPtIssHist(maPtIssEmgDetailDTO, histId, issMode,issType,wcodeId, user); // 출고 이력 남기기
			maPtIssEmgDetailDAO.execPtIss(histId, user); //출고 실행
		}
		//보관창고가 있으면, 입고취소
		if(!"".equals(maPtIssEmgDetailDTO.getToWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTRECHIST_ID");
			String recMode = "R"; 	// 출고취소
			String recType = "MOVE"; // 이동출고
			String wcodeId = maPtIssEmgDetailDTO.getToWcodeId();  //입고창고
			maPtIssEmgDetailDAO.insertPtRecHist(maPtIssEmgDetailDTO, histId, recMode, recType,wcodeId, user);
			maPtIssEmgDetailDAO.execPtRec(histId, user);
		}
		
		maPtIssEmgDetailDAO.issueCancel(maPtIssEmgDetailDTO,user);
		maPtIssEmgDetailDAO.cancelPtIssEmgReqStatus(maPtIssEmgDetailDTO, user);
		
		return new String[]{"S",""};
	}

	@Override
	public String findStockQty(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws Exception {
        return maPtIssEmgDetailDAO.findStockQty(maPtIssEmgDetailDTO, user);
	}
}
