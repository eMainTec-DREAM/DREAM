package dream.part.adj.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import dream.part.adj.dao.MaPtFcRecDetailDAO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.adj.service.MaPtFcRecDetailService;

/**
 * �����԰� - �� serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtFcRecDetailServiceTarget"
 * @spring.txbn id="maPtFcRecDetailService"
 * @spring.property name="maPtFcRecDetailDAO" ref="maPtFcRecDetailDAO"
 */
public class MaPtFcRecDetailServiceImpl implements MaPtFcRecDetailService
{
    private MaPtFcRecDetailDAO maPtFcRecDetailDAO = null;
    
    public MaPtFcRecDetailDAO getMaPtFcRecDetailDAO() 
    {
		return maPtFcRecDetailDAO;
	}

	public void setMaPtFcRecDetailDAO(MaPtFcRecDetailDAO maPtFcRecDetailDAO) 
	{
		this.maPtFcRecDetailDAO = maPtFcRecDetailDAO;
	}

	public MaPtFcRecDetailDTO findDetail(User user, String fcRecListId)throws Exception
    {
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailDAO.findDetail(user, fcRecListId);
        
        return maPtFcRecDetailDTO;
    }
	
	public int insertDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception
    {   
		return maPtFcRecDetailDAO.insertDetail(maPtFcRecDetailDTO);
    }
	
	public int updateDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = maPtFcRecDetailDTO.getCompNo();
	    String fcRecListId = maPtFcRecDetailDTO.getFcRecListId();
	    
	    // ���°� �԰�Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
	    // �԰���� ��ȸ

        String fcRecListStatus = maPtFcRecDetailDAO.findFcRecListStatus(compNo, fcRecListId);

        if(!"C".equals(fcRecListStatus))
        {
	        resultCnt = maPtFcRecDetailDAO.updateDetail(maPtFcRecDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public int updatePtFcRecListStatus(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception
	{     
		
	    String compNo = maPtFcRecDetailDTO.getCompNo();
        String ptRecMode = maPtFcRecDetailDTO.getFcRecMode(); 
        
        //if("C".equals(ptRecMode)) {
        //��ǰ���̺� �ֱٱ����԰�¥ update
        	maPtFcRecDetailDAO.updateGrDate(maPtFcRecDetailDTO,compNo);
        //}
        // �԰�Ϸ����� ���¸� ������ ��� �Ʒ� ���ν����� ȣ���Ѵ�. 
        //if("C".equals(ptRecMode))
        //{
            // �̷� ���̺� id�� ������, �̷µ����� ���� �� ���ν��� ȣ��. 
            String ptRecHistId = maPtFcRecDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtFcRecDetailDAO.insertRecHistory(ptRecHistId, maPtFcRecDetailDTO);
            // �԰�Ϸ� ����, ��ǰ���̺� update ������̺� update/insert
            maPtFcRecDetailDAO.executeSpPtInstock(compNo, ptRecHistId);
        //}
            		//update�ǽ�(������
	    return maPtFcRecDetailDAO.updatePtFcRecListStatus(maPtFcRecDetailDTO);
	}
    
    public String validFcRecListNo(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception
    {
        return maPtFcRecDetailDAO.validFcRecListNo(maPtFcRecDetailDTO);
    }
}
