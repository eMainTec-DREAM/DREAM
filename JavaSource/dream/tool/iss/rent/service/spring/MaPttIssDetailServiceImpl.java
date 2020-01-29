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
 * �����԰� - �� serviceimpl 
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
	    
	    // ���°� �԰�Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
        String preRecListStatus = maPttIssDetailDAO.findPtIssListStatus(compNo, ptIssListId);
        if(!"C".equals(preRecListStatus))
        {
	        resultCnt = maPttIssDetailDAO.updateDetail(maPttIssDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] issuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException
    {
        /** @param bwart �̵����� 201
         * @param budat ������ 20160726
         * @param werks �÷�Ʈ S111
         * @param lgort ������ġ 1190
         * @param gsber ������� S110
         * @param kostl �ڽ�Ʈ���� ��������-->  S21032
         * @param matnr ǰ�� BDMZ-00061
         * @param menge ���� 5
         * @param meins �������� EA
         */
        String rtnValue = "S";
        String[] rtnArr = new String[4];

        //Part Grade :B �� ERP ȣ�� ���� ��� ����

        maPttIssDetailDTO.setPtissStatus("C");
        maPttIssDetailDAO.updatePtIssList(maPttIssDetailDTO);

        String ptisshistId = maPttIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
        maPttIssDetailDAO.insertPtIssHist(maPttIssDetailDTO, ptisshistId, "C");

        //SP_PT_OUTSTOCK �����췯 ����
        maPttIssDetailDAO.execSP_PT_OUTSTOCK(maPttIssDetailDTO, ptisshistId);
        
        //TAPTRENT_STOCK ����
        maPttIssDetailDAO.insertPtRentStock(maPttIssDetailDTO);
        rtnArr[0] = "S";
       

        return rtnArr;
    }
    
	public String[] cancelIssuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException
    {               
        String rtnValue = "S";
        String[] rtnArr = new String[4];

 
            maPttIssDetailDTO.setPtissStatus("X"); //���
            
            String ptisshistId = maPttIssDetailDAO.getNextSequence("SQAPTISSHIST_ID");
            maPttIssDetailDAO.insertPtIssHist(maPttIssDetailDTO, ptisshistId, "R");

            //������ PTISSLIST�� ������Ʈ? 
            maPttIssDetailDAO.updateDetail(maPttIssDetailDTO);
            
            //SP_PT_OUTSTOCK �����췯 ����
            maPttIssDetailDAO.execSP_PT_OUTSTOCK(maPttIssDetailDTO, ptisshistId);
            
            //TAPTRENT_STOCK ����
            maPttIssDetailDAO.updatePtRentStock(maPttIssDetailDTO);
            rtnArr[0] = "S";
        
        
        return rtnArr;
    }
}
