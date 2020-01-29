package dream.part.iss.emg.list.service.spring;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dao.MaPtIssEmgDetailDAO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;

/**
 * ������ - �� serviceimpl 
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
	    //TAPTISSREQ �Է�
	    String ptemgissreqId = maPtIssEmgDetailDAO.getNextSequence("SQAPTEMGISSREQ_ID");
	    maPtIssEmgDetailDTO.setPtemgissreqId(ptemgissreqId);
	    maPtIssEmgDetailDAO.insertPtIssEmgReq(maPtIssEmgDetailDTO, loginUser);
	    //TAPTISSLIST �Է�
	    result = maPtIssEmgDetailDAO.insertPtIssEmgList(maPtIssEmgDetailDTO, loginUser);
	    
        return result;
    }
	
	public int updateDetail(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser) throws Exception
    {   
	    int result = 0;
	    //TAPTISSLIST ����
	    result=+ maPtIssEmgDetailDAO.updatePtIssEmgList(maPtIssEmgDetailDTO);

	    return result;
    }

	public String[] issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException {
		
		//���â�� ������, ���ó��
		if(!"".equals(maPtIssEmgDetailDTO.getWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTISSHIST_ID");
			String issMode = "C"; // ���
			String issType = "MOVE"; // �Ϲ����
			String wcodeId = maPtIssEmgDetailDTO.getWcodeId();  //���â��
			maPtIssEmgDetailDAO.insertPtIssHist(maPtIssEmgDetailDTO, histId, issMode,issType,wcodeId, user); // ��� �̷� �����
			maPtIssEmgDetailDAO.execPtIss(histId, user); //��� ����
		}
		//����â�� ������, �԰�ó��
		if(!"".equals(maPtIssEmgDetailDTO.getToWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTRECHIST_ID");
			String recMode = "C"; 	// �԰�
			String recType = "MOVE"; 
			String wcodeId = maPtIssEmgDetailDTO.getToWcodeId();  //�԰�â��
			maPtIssEmgDetailDAO.insertPtRecHist(maPtIssEmgDetailDTO, histId, recMode, recType,wcodeId, user);
			maPtIssEmgDetailDAO.execPtRec(histId, user);
		}
		maPtIssEmgDetailDAO.issueComp(maPtIssEmgDetailDTO,user); // ���� , �������, ���μ�, �����(TAEMP) UPDATE
		maPtIssEmgDetailDAO.completePtIssEmgReqStatus(maPtIssEmgDetailDTO, user);
		
		return new String[]{"S",""};
	}
	public String[] issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException {
		
		//���â�� ������, ������
		if(!"".equals(maPtIssEmgDetailDTO.getWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTISSHIST_ID");
			String issMode = "R"; // ������
			String issType = "MOVE"; // �̵����
			String wcodeId = maPtIssEmgDetailDTO.getWcodeId();  //���â��
			maPtIssEmgDetailDAO.insertPtIssHist(maPtIssEmgDetailDTO, histId, issMode,issType,wcodeId, user); // ��� �̷� �����
			maPtIssEmgDetailDAO.execPtIss(histId, user); //��� ����
		}
		//����â�� ������, �԰����
		if(!"".equals(maPtIssEmgDetailDTO.getToWcodeId())){
			String histId = maPtIssEmgDetailDAO.getNextSequence("SQAPTRECHIST_ID");
			String recMode = "R"; 	// ������
			String recType = "MOVE"; // �̵����
			String wcodeId = maPtIssEmgDetailDTO.getToWcodeId();  //�԰�â��
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
