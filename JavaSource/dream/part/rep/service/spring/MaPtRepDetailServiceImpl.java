package dream.part.rep.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import dream.part.rep.dao.MaPtRepDetailDAO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;
import dream.part.rep.service.MaPtRepDetailService;

/**
 * 부품수리 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepDetailServiceTarget"
 * @spring.txbn id="maPtRepDetailService"
 * @spring.property name="maPtRepDetailDAO" ref="maPtRepDetailDAO"
 */
public class MaPtRepDetailServiceImpl implements MaPtRepDetailService
{
    private MaPtRepDetailDAO maPtRepDetailDAO = null;
    
    public MaPtRepDetailDAO getMaPtRepDetailDAO() 
    {
		return maPtRepDetailDAO;
	}

	public void setMaPtRepDetailDAO(MaPtRepDetailDAO maPtRepDetailDAO) 
	{
		this.maPtRepDetailDAO = maPtRepDetailDAO;
	}

	public MaPtRepDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)throws Exception
    {
        return maPtRepDetailDAO.findDetail(maPtRepCommonDTO, loginUser);
    }
	
	public int insertDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {   
        
		if("N".equals(MwareConfig.getIsUsePartGrade())){
			maPtRepDetailDTO.setPartGrade(MwareConfig.getPartGrade()); //부품등급을 사용하지 않는 경우는 설정에 있는 부품등급을 사용함.);
		}
		return maPtRepDetailDAO.insertDetail(maPtRepDetailDTO, loginUser);
    }
	
	public int updateDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {        
		if("N".equals(MwareConfig.getIsUsePartGrade())){
			maPtRepDetailDTO.setPartGrade(MwareConfig.getPartGrade()); //부품등급을 사용하지 않는 경우는 설정에 있는 부품등급을 사용함.);
		}
		return maPtRepDetailDAO.updateDetail(maPtRepDetailDTO, loginUser);
    }
	
	public int updatePtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
	{      
	    String ptRepairListStatus = maPtRepDetailDTO.getPtRepairListStatus();
	    
	    // 사내입고, 외주수리입고 처리 
        if("L".equals(ptRepairListStatus) || "V".equals(ptRepairListStatus))
        {
            String ptRecHistId = maPtRepDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtRepDetailDTO.setPtRecHistId(ptRecHistId);
            maPtRepDetailDAO.insertRecHistory(maPtRepDetailDTO, loginUser);
            maPtRepDetailDAO.executeSpPtInstock(maPtRepDetailDTO, loginUser);
        }
        
        String isSerial = maPtRepDetailDTO.getIsSerial();
        
        if("Y".equals(isSerial))
        {
            //시리얼 자재, 외주수리의뢰
            if("S".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"O");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"O");
            }
            //사내수리 완료
            else if("L".equals(ptRepairListStatus)||"V".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"S");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"S");
            }
            //수리 불가
            else if("X".equals(ptRepairListStatus))
            {
            	maPtRepDetailDAO.updateEquipment(maPtRepDetailDTO, loginUser,"D");
            	maPtRepDetailDAO.insertEqHistory(maPtRepDetailDTO, loginUser,"D");
            }
            
        }
        
	    return maPtRepDetailDAO.updatePtRepairListStatus(maPtRepDetailDTO, loginUser);
	}
	
	public int updatePtRepairListStatusCancel(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
	{   
	    // 수리 취소 시 상태 업데이트전, 기존 상태를 조회한다.  
	    String ptRepairListStatus = maPtRepDetailDAO.findPtRepairListStatus(maPtRepDetailDTO, loginUser);
	    
        // 수리대기 상태로 변경하기전, 사내수리(L) or 외주수리(V) 인 경우 입고이력에 "취소"를 등록해 재고가 차감될수있게 한다. 
        if("L".equals(ptRepairListStatus) || "V".equals(ptRepairListStatus))
        {
            String ptRecHistId = maPtRepDetailDAO.getNextSequence("SQAPTRECHIST_ID");
            maPtRepDetailDTO.setPtRecHistId(ptRecHistId);
            maPtRepDetailDAO.insertRecHistory(maPtRepDetailDTO, loginUser);
            maPtRepDetailDAO.executeSpPtInstock(maPtRepDetailDTO, loginUser);
        }
	    // 입고이력 취소 처리 후 상태를 변경한다. 
	    return maPtRepDetailDAO.updatePtRepairListStatus(maPtRepDetailDTO, loginUser);
	}
    
    public String validPtRepairListNo(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception
    {
        return maPtRepDetailDAO.validPtRepairListNo(maPtRepDetailDTO, loginUser);
    }
}
