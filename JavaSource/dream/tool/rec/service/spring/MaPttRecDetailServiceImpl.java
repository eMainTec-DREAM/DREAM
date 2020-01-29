package dream.tool.rec.service.spring;

import common.bean.User;
import dream.tool.rec.dao.MaPttRecDetailDAO;
import dream.tool.rec.dto.MaPttRecDetailDTO;
import dream.tool.rec.service.MaPttRecDetailService;

/**
 * 구매입고 - 상세 serviceimpl 
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
	    
	    // 상태가 입고완료[C]가 아닌 경우만 Update 한다. 
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
        
        // 입고완료으로 상태를 변경할 경우 아래 프로시져를 호출한다. 
        //if("C".equals(ptRecMode))
        {
            // 이력 테이블 id를 생성후, 이력데이터 생성 및 프로시져 호출. 
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
