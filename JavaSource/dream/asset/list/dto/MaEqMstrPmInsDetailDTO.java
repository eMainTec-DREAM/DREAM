package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비의 정기점검  상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId				= "";
	/** 예방작업ID */
	private String pmId					= "";
	/** 예방작업설비ID */
	private String pmEquipId			= "";
	
	/** 예방작업No */
	private String pmNo					= "";
	

	/** 작업종류ID */
	private String woTypeId				= "";
	
	/** 작업형태ID */
	private String pmTypeId				= "";
	/** 작업형태명 */
	private String pmTypeDesc			= "";
	/** 주기 */
	private String cycle				= "";
	/** OLD 주기(UPDATE 시 주기가 변경되었는지 확인할 때 사용) */
	private String oldCycle				= "";
	/** 주기타입ID */
	private String periodTypeId			= "";
	/** OLD 주기타입(UPDATE 시 주기타입이 변경되었는지 확인할 때 사용) */
	private String oldPeriodTypeId		= "";
	/** 주기타입명 */
	private String periodTypeDesc		= "";
	/** 시행여부 ID */
	private String isActiveId			= "";
	/** 시행여부 명 */
	private String isActiveDesc			= "";
	/** 작업시작일 */
	private String initWrkDate			= "";
	/** 작업시작일 (UPDATE 시 시작일이 변경되었는지 확인할 때 사용) */
	private String oldInitWrkDate		= "";
	/** 부서ID */
	private String deptId				= "";
	/** 부서명 */
	private String deptDesc				= "";
	/** 작업그룹ID */
	private String wkCtrId				= "";
	/** 작업그룹명 */
	private String wkCtrDesc			= "";
	/** 담당자ID */
	private String empId				= "";
	/** 담당자명 */
	private String empDesc				= "";
	/** 근무달력ID */
	private String wrkCalListId			= "";
	/** 근무달력명 */
	private String wrkCalListDesc		= "";
	/** revision History ID */
	private String revisionHistId		= "";
	/** 비고 */
	private String remark				= "";
	/** 작업명 */
	private String description			= "";

	/** 공장ID */
	private String plantId				= "";
	/** 공장명 */
	private String plantDesc			= "";
	/** 일정생성방법 ID */
	private String scheduleTypeId		= "";
	/** 일정생성방법명 */
	private String scheduleTypeDesc		= "";
	/** 가동시간 */
	private String usage				= "";
	/** 몇일전작업생성 */
	private String woResBef				= "";
	/** 가동달력 ID */
	private String lnWrkListId			= "";
	/** 가동달력명 */
	private String lnWrkListDesc		= "";
	/** 예상작업시간(분) */
	private String predWoTimeMin		= "";

	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(String scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}
	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getWoResBef() {
		return woResBef;
	}
	public void setWoResBef(String woResBef) {
		this.woResBef = woResBef;
	}
	public String getLnWrkListId() {
		return lnWrkListId;
	}
	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}
	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}
	public void setLnWrkListDesc(String lnWrkListDesc) {
		this.lnWrkListDesc = lnWrkListDesc;
	}
	public String getPredWoTimeMin() {
		return predWoTimeMin;
	}
	public void setPredWoTimeMin(String predWoTimeMin) {
		this.predWoTimeMin = predWoTimeMin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOldInitWrkDate() {
		return oldInitWrkDate;
	}
	public void setOldInitWrkDate(String oldInitWrkDate) {
		this.oldInitWrkDate = oldInitWrkDate;
	}
	public String getOldCycle() {
		return oldCycle;
	}
	public void setOldCycle(String oldCycle) {
		this.oldCycle = oldCycle;
	}
	public String getOldPeriodTypeId() {
		return oldPeriodTypeId;
	}
	public void setOldPeriodTypeId(String oldPeriodTypeId) {
		this.oldPeriodTypeId = oldPeriodTypeId;
	}
	public String getRevisionHistId() {
		return revisionHistId;
	}
	public void setRevisionHistId(String revisionHistId) {
		this.revisionHistId = revisionHistId;
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(String periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getIsActiveId() {
		return isActiveId;
	}
	public void setIsActiveId(String isActiveId) {
		this.isActiveId = isActiveId;
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getInitWrkDate() {
		return initWrkDate;
	}
	public void setInitWrkDate(String initWrkDate) {
		this.initWrkDate = CommonUtil.convertDate(initWrkDate);
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getWrkCalListId() {
		return wrkCalListId;
	}
	public void setWrkCalListId(String wrkCalListId) {
		this.wrkCalListId = wrkCalListId;
	}
	public String getWrkCalListDesc() {
		return wrkCalListDesc;
	}
	public void setWrkCalListDesc(String wrkCalListDesc) {
		this.wrkCalListDesc = wrkCalListDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}