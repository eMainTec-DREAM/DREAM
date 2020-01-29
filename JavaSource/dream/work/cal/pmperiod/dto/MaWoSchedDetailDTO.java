package dream.work.cal.pmperiod.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방작업일정(기간) - 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoSchedDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaWoSchedDetailDTO extends BaseDTO
{
	/** 스케쥴ID */
	private String pmSchedId 			= "";
	/** PM ID */
	private String pmId					= "";
	/** PM번호 */
	private String pmNo 				= "";
	/** 설비 */
	private String equipDesc	 		= "";
	/** 계획일정 */
	private String planDate	 			= "";
	/** PM명 */
	private String pmDesc 				= "";
	/** 가동량/시간 구분 */
	private String scheduleType	 		= "";
	/** 부서명 */
	private String deptDesc	 			= "";
	/** 주기 */
	private String cycle	 			= "";
	/** 가동량(시간) */
	private String usage	 			= "";
	/** 주기구분(주/월/년) */
	private String periodType	 		= "";
	/** 작업형태 */
	private String pmType	 			= "";
	/** 비고 */
	private String remark	 			= "";
	/** 일정조정 */
	private String schedDate 			= "";
	/** 상태 */
	private String pmSchedStatus		= "";
	/** 작업ID */
	private String wkorId				= "";
	/** param */
	private String param				= "";
	
	
    public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getPmSchedStatus() {
		return pmSchedStatus;
	}
	public void setPmSchedStatus(String pmSchedStatus) {
		this.pmSchedStatus = pmSchedStatus;
	}
	public String getSchedDate() {
		return schedDate;
	}
	public void setSchedDate(String schedDate) {
		this.schedDate = CommonUtil.convertDate(schedDate);
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = CommonUtil.convertDate(planDate);
	}
	public String getPmSchedId() {
		return pmSchedId;
	}
	public void setPmSchedId(String pmSchedId) {
		this.pmSchedId = pmSchedId;
		super.setAuditKey(pmSchedId);
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
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getPmDesc() {
		return pmDesc;
	}
	public void setPmDesc(String pmDesc) {
		this.pmDesc = pmDesc;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPmType() {
		return pmType;
	}
	public void setPmType(String pmType) {
		this.pmType = pmType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
