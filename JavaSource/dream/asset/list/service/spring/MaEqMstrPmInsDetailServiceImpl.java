package dream.asset.list.service.spring;

import common.bean.User;
import common.util.DateUtil;
import dream.asset.list.dao.MaEqMstrPmInsDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;
import dream.asset.list.service.MaEqMstrPmInsDetailService;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pm.list.service.WorkPmListEquipDetailService;

/**
 * 설비 예방작업 상세
 * @author kim2107
 * @version $Id: MaEqMstrPmInsDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPmInsDetailServiceTarget"
 * @spring.txbn id="maEqMstrPmInsDetailService"
 * @spring.property name="maEqMstrPmInsDetailDAO" ref="maEqMstrPmInsDetailDAO"
 * @spring.property name="maPmMstrDetailService" ref="maPmMstrDetailService"
 * @spring.property name="workPmListEquipDetailService" ref="workPmListEquipDetailService"
 */
public class MaEqMstrPmInsDetailServiceImpl implements MaEqMstrPmInsDetailService
{
    private MaEqMstrPmInsDetailDAO maEqMstrPmInsDetailDAO = null;
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

	public MaEqMstrPmInsDetailDAO getMaEqMstrPmInsDetailDAO() {
		return maEqMstrPmInsDetailDAO;
	}

	public void setMaEqMstrPmInsDetailDAO(MaEqMstrPmInsDetailDAO maEqMstrPmInsDetailDAO) {
		this.maEqMstrPmInsDetailDAO = maEqMstrPmInsDetailDAO;
	}

	@Override
	public MaEqMstrPmInsDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO,
			User user) throws Exception {
		return maEqMstrPmInsDetailDAO.findDetail(maEqMstrCommonDTO, maEqMstrPmInsListDTO, user);
	}

	@Override
	public int updateDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user)
			throws Exception {
		int result = 0;
		//PMLIST UPDATE
		MaPmMstrDetailDTO maPmMstrDetailDTO = setMaPmMstrDetailDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO,user);
		result+=maPmMstrDetailService.updateDetail(maPmMstrDetailDTO, user);
		
		//PMEQUIP UPDATE
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = setWorkPmListEquipDetailDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO,user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO, user);
		result+=workPmListEquipDetailService.updateDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		
		return result;
	}


	@Override
	public int insertDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user)
			throws Exception {
		
		int result = 0;
		
		MaPmMstrDetailDTO maPmMstrDetailDTO = setMaPmMstrDetailDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO,user);
		result += maPmMstrDetailService.insertDetail(maPmMstrDetailDTO, user);
		
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = setWorkPmListEquipDetailDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO,user);
		MaPmMstrCommonDTO maPmMstrCommonDTO = setMaPmMstrCommonDTO(maEqMstrCommonDTO, maEqMstrPmInsDetailDTO, user);
	
		result += workPmListEquipDetailService.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		
		return result;
	}

	private MaPmMstrDetailDTO setMaPmMstrDetailDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user) {
		MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
		
		maPmMstrDetailDTO.setPmId(maEqMstrPmInsDetailDTO.getPmId());
		maPmMstrDetailDTO.setCompNo(user.getCompNo());
		maPmMstrDetailDTO.setPmNo(maEqMstrPmInsDetailDTO.getPmNo());
		maPmMstrDetailDTO.setDescription(maEqMstrPmInsDetailDTO.getDescription());
		maPmMstrDetailDTO.setDeptId(maEqMstrPmInsDetailDTO.getDeptId());
		maPmMstrDetailDTO.setRevisionStatusId("W");
		maPmMstrDetailDTO.setPmType(maEqMstrPmInsDetailDTO.getPmTypeId());
//		maPmMstrDetailDTO.setScheduleType("T");
//		maPmMstrDetailDTO.setOldScheduleType("T");
		maPmMstrDetailDTO.setCycle(maEqMstrPmInsDetailDTO.getCycle());
		maPmMstrDetailDTO.setOldCycle(maEqMstrPmInsDetailDTO.getCycle());
		maPmMstrDetailDTO.setPeriodType(maEqMstrPmInsDetailDTO.getPeriodTypeId());
		maPmMstrDetailDTO.setOldPeriodType(maEqMstrPmInsDetailDTO.getOldPeriodTypeId());
//		maPmMstrDetailDTO.setWoResBef("14");
		maPmMstrDetailDTO.setInitWrkDate(maEqMstrPmInsDetailDTO.getInitWrkDate());
		maPmMstrDetailDTO.setIsLastVersion("N");
		maPmMstrDetailDTO.setWoType(maEqMstrPmInsDetailDTO.getWoTypeId());
		maPmMstrDetailDTO.setEmpId(maEqMstrPmInsDetailDTO.getEmpId());
		maPmMstrDetailDTO.setIsActive(maEqMstrPmInsDetailDTO.getIsActiveId());
		maPmMstrDetailDTO.setWkCtrId(maEqMstrPmInsDetailDTO.getWkCtrId());
		maPmMstrDetailDTO.setWrkcalListId(maEqMstrPmInsDetailDTO.getWrkCalListId());
		maPmMstrDetailDTO.setWorkNumber("1");
		maPmMstrDetailDTO.setUpdDate(DateUtil.getDateTime());
		maPmMstrDetailDTO.setCreDate(DateUtil.getDateTime());
		maPmMstrDetailDTO.setScheduleType(maEqMstrPmInsDetailDTO.getScheduleTypeId());
		maPmMstrDetailDTO.setOldScheduleType(maEqMstrPmInsDetailDTO.getScheduleTypeId());
		maPmMstrDetailDTO.setWoResBef(maEqMstrPmInsDetailDTO.getWoResBef());
		maPmMstrDetailDTO.setPredWoTimeMin(maEqMstrPmInsDetailDTO.getPredWoTimeMin());
		maPmMstrDetailDTO.setLnWrkListId(maEqMstrPmInsDetailDTO.getLnWrkListId());
		maPmMstrDetailDTO.setUsage(maEqMstrPmInsDetailDTO.getUsage());
		maPmMstrDetailDTO.setPlantId(maEqMstrPmInsDetailDTO.getPlantId());
		maPmMstrDetailDTO.setRemark(maEqMstrPmInsDetailDTO.getRemark());
		
		return maPmMstrDetailDTO;
	}
	
	private WorkPmListEquipDetailDTO setWorkPmListEquipDetailDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user) {
		
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
		workPmListEquipDetailDTO.setPmEquipId(maEqMstrPmInsDetailDTO.getPmEquipId());
		workPmListEquipDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
		workPmListEquipDetailDTO.setInitWrkDate(maEqMstrPmInsDetailDTO.getInitWrkDate());
		workPmListEquipDetailDTO.setOldInitWrkDate(maEqMstrPmInsDetailDTO.getOldInitWrkDate());
		workPmListEquipDetailDTO.setIsActive(maEqMstrPmInsDetailDTO.getIsActiveId());
		workPmListEquipDetailDTO.setRemark(maEqMstrPmInsDetailDTO.getRemark());
		
		return workPmListEquipDetailDTO;
	}
	private MaPmMstrCommonDTO setMaPmMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO,MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, User user) {
		
		MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
		maPmMstrCommonDTO.setPmId(maEqMstrPmInsDetailDTO.getPmId());
		maPmMstrCommonDTO.setWoType(maEqMstrPmInsDetailDTO.getWoTypeId());
		maPmMstrCommonDTO.setCompNo(user.getCompNo());
		return maPmMstrCommonDTO;
	}
}
