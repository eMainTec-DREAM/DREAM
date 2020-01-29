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
 * ���ⱸ�ݳ� - �� serviceimpl 
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
	    
	    // ���°� �ݳ��Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
        String ptRtnListStatus = maPttRtnDetailDAO.findPtRtnListStatus(compNo, ptRtnListId);
        if(!"C".equals(ptRtnListStatus))
        {
	        resultCnt = maPttRtnDetailDAO.updateDetail(maPttRtnDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] rtnPart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException
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

        maPttRtnDetailDTO.setPtRtnStatus("C");
        maPttRtnDetailDAO.updatePtRtnList(maPttRtnDetailDTO);

        //String ptisshistId = maPttRtnDetailDAO.getNextSequence("SQAPTRTNHIST_ID");
        //maPttRtnDetailDAO.insertPtIssHist(maPttRtnDetailDTO, ptisshistId, "C");

        //����������
        maPttRtnDetailDAO.updatePtStock(maPttRtnDetailDTO);
        //TAPTRENT_STOCK ����
        maPttRtnDetailDAO.insertPtRentStock(maPttRtnDetailDTO);
        rtnArr[0] = "S";
       

        return rtnArr;
    }
    
	public String[] cancelIssuePart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException
    {               
        String rtnValue = "S";
        String[] rtnArr = new String[4];

 
            maPttRtnDetailDTO.setPtRtnStatus("X"); //���
            
            String ptisshistId = maPttRtnDetailDAO.getNextSequence("SQAPTRTNHIST_ID");
            maPttRtnDetailDAO.insertPtIssHist(maPttRtnDetailDTO, ptisshistId, "R");

            //������ PTRTNLIST�� ������Ʈ? 
            maPttRtnDetailDAO.updateDetail(maPttRtnDetailDTO);
            
            //����������
            maPttRtnDetailDAO.cancelPtStock(maPttRtnDetailDTO);
            
            //TAPTRENT_STOCK ����
            maPttRtnDetailDAO.cancelPtRentStock(maPttRtnDetailDTO);
            rtnArr[0] = "S";
        
            
        return rtnArr;
    }
}
