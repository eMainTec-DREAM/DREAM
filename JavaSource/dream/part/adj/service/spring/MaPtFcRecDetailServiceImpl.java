package dream.part.adj.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import dream.part.adj.dao.MaPtFcRecDetailDAO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.adj.service.MaPtFcRecDetailService;

/**
 * 무상입고 - 상세 serviceimpl 
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
	    
	    // 상태가 입고완료[C]가 아닌 경우만 Update 한다. 
	    // 입고상태 조회

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
        //부품테이블 최근구매입고날짜 update
        	maPtFcRecDetailDAO.updateGrDate(maPtFcRecDetailDTO,compNo);
        //}
        // 입고완료으로 상태를 변경할 경우 아래 프로시져를 호출한다. 
        //if("C".equals(ptRecMode))
        //{
            // 이력 테이블 id를 생성후, 이력데이터 생성 및 프로시져 호출. 
            String ptRecHistId = maPtFcRecDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtFcRecDetailDAO.insertRecHistory(ptRecHistId, maPtFcRecDetailDTO);
            // 입고완료 전에, 부품테이블 update 재고테이블 update/insert
            maPtFcRecDetailDAO.executeSpPtInstock(compNo, ptRecHistId);
        //}
            		//update실시(무상입
	    return maPtFcRecDetailDAO.updatePtFcRecListStatus(maPtFcRecDetailDTO);
	}
    
    public String validFcRecListNo(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception
    {
        return maPtFcRecDetailDAO.validFcRecListNo(maPtFcRecDetailDTO);
    }
}
