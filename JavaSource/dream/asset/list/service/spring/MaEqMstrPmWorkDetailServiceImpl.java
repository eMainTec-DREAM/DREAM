package dream.asset.list.service.spring;

import common.bean.User;
import common.util.DateUtil;
import dream.asset.list.dao.MaEqMstrPmWorkDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;
import dream.asset.list.service.MaEqMstrPmWorkDetailService;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pm.list.service.WorkPmListEquipDetailService;

/**
 * 설비 예방작업 상세
 * @author kim2107
 * @version $Id: MaEqMstrPmWorkDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmWorkDetailServiceTarget"
 * @spring.txbn id="maEqMstrPmWorkDetailService"
 * @spring.property name="maEqMstrPmWorkDetailDAO" ref="maEqMstrPmWorkDetailDAO"
 * @spring.property name="maPmMstrDetailService" ref="maPmMstrDetailService"
 * @spring.property name="workPmListEquipDetailService" ref="workPmListEquipDetailService"
 */
public class MaEqMstrPmWorkDetailServiceImpl implements MaEqMstrPmWorkDetailService
{
    private MaEqMstrPmWorkDetailDAO maEqMstrPmWorkDetailDAO = null;
    private MaPmMstrDetailService maPmMstrDetailService = null;
    private WorkPmListEquipDetailService workPmListEquipDetailService = null;
    
	public WorkPmListEquipDetailService getWorkPmListEquipDetailService() {
		return workPmListEquipDetailService;
	}

	public void setWorkPmListEquipDetailService(WorkPmListEquipDetailService workPmListEquipDetailService) {
		this.workPmListEquipDetailService = workPmListEquipDetailService;
	}

	public MaPmMstrDetailService getMaPmMstrDetailService() {
		return maPmMstrDetailService;
	}

	public void setMaPmMstrDetailService(MaPmMstrDetailService maPmMstrDetailService) {
		this.maPmMstrDetailService = maPmMstrDetailService;
	}

	public MaEqMstrPmWorkDetailDAO getMaEqMstrPmWorkDetailDAO() {
		return maEqMstrPmWorkDetailDAO;
	}

	public void setMaEqMstrPmWorkDetailDAO(MaEqMstrPmWorkDetailDAO maEqMstrPmWorkDetailDAO) {
		this.maEqMstrPmWorkDetailDAO = maEqMstrPmWorkDetailDAO;
	}

	@Override
	public MaEqMstrPmWorkDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO,
			User user) throws Exception {
		return maEqMstrPmWorkDetailDAO.findDetail(maEqMstrCommonDTO, maEqMstrPmWorkListDTO, user);
	}

	@Override
	public int updateDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user)
			throws Exception {
		int result = 0;
		//PMLIST UPDATE
		MaPmMstrDetailDTO maPmMstrDetailDTO = setMaPmMstrDetailDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO,user);
		result+=maPmMstrDetailService.updateDetail(maPmMstrDetailDTO, user);
		
		//PMEQUIP UPDATE
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = setWorkPmListEquipDetailDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO,user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO, user);
		result+=workPmListEquipDetailService.updateDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		
		return result;
	}


	@Override
	public int insertDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user)
			throws Exception {
		
		int result = 0;
		
		MaPmMstrDetailDTO maPmMstrDetailDTO = setMaPmMstrDetailDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO,user);
		result += maPmMstrDetailService.insertDetail(maPmMstrDetailDTO, user);
		
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = setWorkPmListEquipDetailDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO,user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrCommonDTO, maEqMstrPmWorkDetailDTO, user);
	
		result += workPmListEquipDetailService.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		
		return result;
	}

	private MaPmMstrDetailDTO setMaPmMstrDetailDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) {
		MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
		
		maPmMstrDetailDTO.setPmId(maEqMstrPmWorkDetailDTO.getPmId());
		maPmMstrDetailDTO.setCompNo(user.getCompNo());
		maPmMstrDetailDTO.setPmNo(maEqMstrPmWorkDetailDTO.getPmNo());
		maPmMstrDetailDTO.setDescription(maEqMstrPmWorkDetailDTO.getDescription());
		maPmMstrDetailDTO.setDeptId(maEqMstrPmWorkDetailDTO.getDeptId());
		maPmMstrDetailDTO.setRevisionStatusId("W");
		maPmMstrDetailDTO.setPmType(maEqMstrPmWorkDetailDTO.getPmTypeId());
//		maPmMstrDetailDTO.setScheduleType("T");
//		maPmMstrDetailDTO.setOldScheduleType("T");
		maPmMstrDetailDTO.setCycle(maEqMstrPmWorkDetailDTO.getCycle());
		maPmMstrDetailDTO.setOldCycle(maEqMstrPmWorkDetailDTO.getCycle());
		maPmMstrDetailDTO.setPeriodType(maEqMstrPmWorkDetailDTO.getPeriodTypeId());
		maPmMstrDetailDTO.setOldPeriodType(maEqMstrPmWorkDetailDTO.getOldPeriodTypeId());
//		maPmMstrDetailDTO.setWoResBef("14");
		maPmMstrDetailDTO.setInitWrkDate(maEqMstrPmWorkDetailDTO.getInitWrkDate());
		maPmMstrDetailDTO.setIsLastVersion("N");
		maPmMstrDetailDTO.setWoType(maEqMstrPmWorkDetailDTO.getWoTypeId());
		maPmMstrDetailDTO.setEmpId(maEqMstrPmWorkDetailDTO.getEmpId());
		maPmMstrDetailDTO.setIsActive(maEqMstrPmWorkDetailDTO.getIsActiveId());
		maPmMstrDetailDTO.setWkCtrId(maEqMstrPmWorkDetailDTO.getWkCtrId());
		maPmMstrDetailDTO.setWrkcalListId(maEqMstrPmWorkDetailDTO.getWrkCalListId());
		maPmMstrDetailDTO.setWorkNumber("1");
		maPmMstrDetailDTO.setUpdDate(DateUtil.getDateTime());
		maPmMstrDetailDTO.setCreDate(DateUtil.getDateTime());
		maPmMstrDetailDTO.setScheduleType(maEqMstrPmWorkDetailDTO.getScheduleTypeId());
		maPmMstrDetailDTO.setOldScheduleType(maEqMstrPmWorkDetailDTO.getScheduleTypeId());
		maPmMstrDetailDTO.setWoResBef(maEqMstrPmWorkDetailDTO.getWoResBef());
		maPmMstrDetailDTO.setPredWoTimeMin(maEqMstrPmWorkDetailDTO.getPredWoTimeMin());
		maPmMstrDetailDTO.setLnWrkListId(maEqMstrPmWorkDetailDTO.getLnWrkListId());
		maPmMstrDetailDTO.setUsage(maEqMstrPmWorkDetailDTO.getUsage());
		maPmMstrDetailDTO.setPlantId(maEqMstrPmWorkDetailDTO.getPlantId());
		maPmMstrDetailDTO.setRemark(maEqMstrPmWorkDetailDTO.getRemark());
		
		return maPmMstrDetailDTO;
	}
	
	private WorkPmListEquipDetailDTO setWorkPmListEquipDetailDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) {
		
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
		workPmListEquipDetailDTO.setPmEquipId(maEqMstrPmWorkDetailDTO.getPmEquipId());
		workPmListEquipDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
		workPmListEquipDetailDTO.setInitWrkDate(maEqMstrPmWorkDetailDTO.getInitWrkDate());
		workPmListEquipDetailDTO.setOldInitWrkDate(maEqMstrPmWorkDetailDTO.getOldInitWrkDate());
		workPmListEquipDetailDTO.setIsActive(maEqMstrPmWorkDetailDTO.getIsActiveId());
		workPmListEquipDetailDTO.setRemark(maEqMstrPmWorkDetailDTO.getRemark());
		
		return workPmListEquipDetailDTO;
	}
	private MaPmMstrCommonDTO setMaPmMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) {
		
		MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
		maPmMstrCommonDTO.setPmId(maEqMstrPmWorkDetailDTO.getPmId());
		maPmMstrCommonDTO.setWoType(maEqMstrPmWorkDetailDTO.getWoTypeId());
		maPmMstrCommonDTO.setCompNo(user.getCompNo());
		return maPmMstrCommonDTO;
	}
}
