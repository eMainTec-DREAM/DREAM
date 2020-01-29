package dream.tool.rec.service.spring;

import common.bean.User;
import dream.tool.rec.dao.MaPttRecDetailDAO;
import dream.tool.rec.dto.MaPttRecDetailDTO;
import dream.tool.rec.service.MaPttRecDetailService;

/**
 * �����԰� - �� serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttRecDetailServiceTarget"
 * @spring.txbn id="maPttRecDetailService"
 * @spring.property name="maPttRecDetailDAO" ref="maPttRecDetailDAO"
 */
public class MaPttRecDetailServiceImpl implements MaPttRecDetailService
{
    private MaPttRecDetailDAO maPttRecDetailDAO = null;
    
    public MaPttRecDetailDAO getMaPttRecDetailDAO() 
    {
		return maPttRecDetailDAO;
	}

	public void setMaPttRecDetailDAO(MaPttRecDetailDAO maPttRecDetailDAO) 
	{
		this.maPttRecDetailDAO = maPttRecDetailDAO;
	}

	public MaPttRecDetailDTO findDetail(User user, String ptRecListId)throws Exception
    {
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailDAO.findDetail(user, ptRecListId);
        
        return maPttRecDetailDTO;
    }
	
	public int insertDetail(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception
    {   
        return maPttRecDetailDAO.insertDetail(maPttRecDetailDTO);
    }
	
	public int updateDetail(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = maPttRecDetailDTO.getCompNo();
	    String prRecListId = maPttRecDetailDTO.getPrRecListId();
	    
	    // ���°� �԰�Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
        String preRecListStatus = maPttRecDetailDAO.findPrRecListStatus(compNo, prRecListId);
        if(!"C".equals(preRecListStatus))
        {
	        resultCnt = maPttRecDetailDAO.updateDetail(maPttRecDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public int updatePtRecListStatus(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception
	{     
	    String compNo = maPttRecDetailDTO.getCompNo();
        String ptRecMode = maPttRecDetailDTO.getPtRecMode(); 
        
        // �԰�Ϸ����� ���¸� ������ ��� �Ʒ� ���ν����� ȣ���Ѵ�. 
        //if("C".equals(ptRecMode))
        {
            // �̷� ���̺� id�� ������, �̷µ����� ���� �� ���ν��� ȣ��. 
            String ptRecHistId = maPttRecDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPttRecDetailDAO.insertRecHistory(ptRecHistId, maPttRecDetailDTO);
            maPttRecDetailDAO.executeSpPtInstock(compNo, ptRecHistId);
        }
	    return maPttRecDetailDAO.updatePtRecListStatus(maPttRecDetailDTO);
	}
    
    public String validPrRecListNo(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception
    {
        return maPttRecDetailDAO.validPrRecListNo(maPttRecDetailDTO);
    }
}
