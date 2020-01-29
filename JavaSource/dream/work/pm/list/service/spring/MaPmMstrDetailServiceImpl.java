package dream.work.pm.list.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dao.WorkPmListEquipDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.MaPmMstrDetailService;

/**
 * 상세 serviceimpl 
 * @author  jung7126
 * @version $Id: MaPmMstrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrDetailServiceTarget"
 * @spring.txbn id="maPmMstrDetailService"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 * @spring.property name="workPmListEquipDetailDAO" ref="workPmListEquipDetailDAO"
 */
public class MaPmMstrDetailServiceImpl implements MaPmMstrDetailService
{
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    private WorkPmListEquipDetailDAO workPmListEquipDetailDAO = null;
    
	public WorkPmListEquipDetailDAO getWorkPmListEquipDetailDAO() {
		return workPmListEquipDetailDAO;
	}

	public void setWorkPmListEquipDetailDAO(WorkPmListEquipDetailDAO workPmListEquipDetailDAO) {
		this.workPmListEquipDetailDAO = workPmListEquipDetailDAO;
	}

	public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}

	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}

	public MaPmMstrDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)throws Exception
    {
		
		MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailDAO.findDetail(maPmMstrCommonDTO,user);
		
		if ("PMU".equals(maPmMstrCommonDTO.getWoType())) {
			MaPmMstrDetailDTO maPmMstrPmuDetailDTO = maPmMstrDetailDAO.findEquipDetail(maPmMstrCommonDTO,user);
			maPmMstrDetailDTO.setPmEquipId(maPmMstrPmuDetailDTO.getPmEquipId());
			maPmMstrDetailDTO.setOldEquipId(maPmMstrPmuDetailDTO.getEquipId());
			maPmMstrDetailDTO.setEquipId(maPmMstrPmuDetailDTO.getEquipId());
			maPmMstrDetailDTO.setEquipDesc(maPmMstrPmuDetailDTO.getEquipDesc());
			maPmMstrDetailDTO.setOldInitWrkDate(CommonUtil.getRowDateToNum(maPmMstrPmuDetailDTO.getInitWrkDate()));
			maPmMstrDetailDTO.setInitWrkDate(CommonUtil.getRowDateToNum(maPmMstrPmuDetailDTO.getInitWrkDate()));
		}
		
        return maPmMstrDetailDTO;
    }
	
	public int insertDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {   
		maPmMstrDetailDAO.insertDetail(maPmMstrDetailDTO, user);
		
		// PMU(사용량측정)은 설비탭을 따로두지않고 상세화면에서 입력
		if ("PMU".equals(maPmMstrDetailDTO.getWoType())) {
			MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
			WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
			maPmMstrCommonDTO.setCompNo(maPmMstrDetailDTO.getCompNo());
			maPmMstrCommonDTO.setPmId(maPmMstrDetailDTO.getPmId());
			workPmListEquipDetailDTO.setPmEquipId(maPmMstrDetailDAO.getNextSequence("SQAPMEQUIP_ID"));
			workPmListEquipDetailDTO.setEquipId(maPmMstrDetailDTO.getEquipId());
			workPmListEquipDetailDTO.setDescription(maPmMstrDetailDTO.getEquipDesc());
			workPmListEquipDetailDTO.setInitWrkDate(CommonUtil.convertDate(maPmMstrDetailDTO.getInitWrkDate()));
			workPmListEquipDetailDTO.setIsActive(maPmMstrDetailDTO.getIsActive());
			workPmListEquipDetailDAO.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		}
		
		if("N".equals(MwareConfig.getIsUsePmRevision())){
        	insertRevisionHistAndUpdateMstr(maPmMstrDetailDTO, user);
        	maPmMstrDetailDTO.setIsLastVersion("Y");
        }
		
		if("Y".equals(maPmMstrDetailDTO.getIsActive())&&"Y".equals(maPmMstrDetailDTO.getIsLastVersion())){
    		maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrDetailDTO.getPmId(), user.getEmpId());
    		return maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrDetailDTO.getPmId());
    	}else{
    		return 0;
    	}
    }
	
	public String checkDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {  
		return maPmMstrDetailDAO.checkDetail(maPmMstrDetailDTO, user);
    }
	
	public int updateDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception
    {
		int rtnValue = 0;
		String woType = maPmMstrDetailDTO.getWoType();
		String pmType = maPmMstrDetailDTO.getPmType();
		
		String oldCycle = maPmMstrDetailDTO.getOldCycle();
		String oldPeriodType = maPmMstrDetailDTO.getOldPeriodType();
		String oldSchedleType = maPmMstrDetailDTO.getOldScheduleType();
		
		rtnValue += maPmMstrDetailDAO.updateDetail(maPmMstrDetailDTO, user);
		
		// PMU(사용량측정)은 설비탭을 따로두지않고 상세화면에서 입력
		if ("PMU".equals(maPmMstrDetailDTO.getWoType())) {
			MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
			maPmMstrCommonDTO.setCompNo(maPmMstrDetailDTO.getCompNo());
			maPmMstrCommonDTO.setPmId(maPmMstrDetailDTO.getPmId());
			WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
			workPmListEquipDetailDTO.setEquipId(maPmMstrDetailDTO.getEquipId());
			workPmListEquipDetailDTO.setInitWrkDate(CommonUtil.convertDate(maPmMstrDetailDTO.getInitWrkDate()));
			workPmListEquipDetailDTO.setIsActive(maPmMstrDetailDTO.getIsActive());
			// 제/개정을 사용할때에는 처음에 tapmlst만 생성해주고 pmequip은 생성안해줌
			if ("0".equals(workPmListEquipDetailDAO.countPmEquip(maPmMstrCommonDTO, user))) {
				workPmListEquipDetailDTO.setPmEquipId(maPmMstrDetailDAO.getNextSequence("SQAPMEQUIP_ID"));
				workPmListEquipDetailDAO.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
			} else {
				workPmListEquipDetailDTO.setPmEquipId(maPmMstrDetailDTO.getPmEquipId());
				workPmListEquipDetailDAO.updateDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
			}
			
			if("Y".equals(workPmListEquipDetailDTO.getIsActive())){
	        	if(!workPmListEquipDetailDTO.getOldInitWrkDate().replaceAll("-", "").equals(workPmListEquipDetailDTO.getInitWrkDate().replaceAll("-", ""))
	        			|| !maPmMstrDetailDTO.getOldEquipId().equals(maPmMstrDetailDTO.getEquipId())){
	        		// 시작일자를 변경했으므로 스케쥴 날짜도 변경해야 함.
	        		rtnValue = workPmListEquipDetailDAO.updateLastSchedDate(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
	        	}
	        }
		}
		
		//부서, 작업그룹, 담당자가 변경되었다면 발행된 작업에도 일괄 변경시킴.
		if("PMI".equals(woType) && "RINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmRinsScheduleDetail(maPmMstrDetailDTO, user);
		}else if("PMI".equals(woType) && "EINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmRinsScheduleDetail(maPmMstrDetailDTO, user);
		}else if("PMI".equals(woType) && "DINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmDinsScheduleDetail(maPmMstrDetailDTO, user);
		}else if("PMI".equals(woType) && "CINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmDinsScheduleDetail(maPmMstrDetailDTO, user);
		}else if("PMI".equals(woType) && "PINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmPatrlScheduleDetail(maPmMstrDetailDTO, user);
		}else if("PMI".equals(woType) && "HINS".equals(pmType)){
			rtnValue = maPmMstrDetailDAO.updatePmDinsScheduleDetail(maPmMstrDetailDTO, user);
		}else{
			rtnValue = maPmMstrDetailDAO.updatePmWoScheduleDetail(maPmMstrDetailDTO, user);
		}
		
		if("N".equals(maPmMstrDetailDTO.getIsActive())){
			
			maPmMstrDetailDAO.deletePmScheduleAll(maPmMstrDetailDTO.getCompNo(),maPmMstrDetailDTO.getPmId());
			return 0;
			
    	}else if("Y".equals(maPmMstrDetailDTO.getIsActive())&&"Y".equals(maPmMstrDetailDTO.getIsLastVersion())){
    		
    		if(!oldCycle.equals(maPmMstrDetailDTO.getCycle()) ||
    				!oldPeriodType.equals(maPmMstrDetailDTO.getPeriodType()) ||
    				    !oldSchedleType.equals(maPmMstrDetailDTO.getScheduleType()) 
    		   ){
    			//주기가 바뀌었으므로 일정생성 LAST_DATE을 변경하고 스케쥴을 생성해야 함.
    			rtnValue += maPmMstrDetailDAO.updateLastSchedDate(maPmMstrDetailDTO, user);
    		}
    		
    		maPmMstrDetailDAO.createPmSchedule(maPmMstrDetailDTO.getCompNo(), maPmMstrDetailDTO.getPmId(), maPmMstrDetailDTO.getEnterBy());
    		maPmMstrDetailDAO.createWorkOrder(maPmMstrDetailDTO.getCompNo(), maPmMstrDetailDTO.getPmId());
    		
    	}
		
		
		return rtnValue;
    }

	@Override
	public int insertRevisionHistAndUpdateMstr(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception {
	    CommRevService commRevService = (CommRevService) CommonUtil.getBean("commRevService", user);
	    
		String histId = maPmMstrDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		int result = 0;
		
		CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
		commRevCommonDTO.setRevisionhistId(histId);
        commRevCommonDTO.setCompNo(user.getCompNo());
        commRevCommonDTO.setObjectId(maPmMstrDetailDTO.getPmId());
        commRevCommonDTO.setObjectNo(maPmMstrDetailDTO.getPmNo());
        commRevCommonDTO.setRevisionObjType("PMSTD");
        commRevCommonDTO.setRevisionStatusId("C");
        commRevCommonDTO.setRevisionStepStatusId("CMP");
        commRevCommonDTO.setRevisionType("C");
        commRevCommonDTO.setRevNo("1.00");
        commRevCommonDTO.setWrkDate(DateUtil.getDateTime("yyyyMMdd"));
        commRevCommonDTO.setWrkEmpId(user.getEmpId());
        commRevCommonDTO.setRevDesc(maPmMstrDetailDTO.getDescription());
        commRevService.insertRevHist(commRevCommonDTO, user);
        
		result+= maPmMstrDetailDAO.updatePmMstrLastVersion(maPmMstrDetailDTO, user, histId);
		
		return result;
	}

	@Override
	public String pmNoCheck(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception {
		return maPmMstrDetailDAO.pmNoCheck(maPmMstrDetailDTO, user);
	}
}
