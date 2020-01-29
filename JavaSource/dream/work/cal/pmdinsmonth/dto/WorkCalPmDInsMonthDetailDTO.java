package dream.work.cal.pmdinsmonth.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 월간예방일정 - 상세 DTO
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class WorkCalPmDInsMonthDetailDTO extends BaseDTO
{
	/** 스케쥴ID */
	private String pmInsDSchedId 		= "";
	/** 점검서ID */
	private String pmInsDListId 		= "";
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
	/** 가동량/시간 구분 */
	private String scheduleTypeDesc		= "";
	/** 부서명 */
	private String deptDesc	 			= "";
	/** 주기 */
	private String cycle	 			= "";
	/** 가동량(시간) */
	private String usage	 			= "";
	/** 주기구분(주/월/년) */
	private String periodType	 		= "";
	/** 주기구분(주/월/년) */
	private String periodTypeDesc 		= "";
	/** 작업형태 */
	private String pmType	 			= "";
	/** 작업형태 */
	private String pmTypeDesc 			= "";
	/** 비고 */
	private String remark	 			= "";
	/** 일정조정 */
	private String schedDate 			= "";
	/** 상태 */
	private String pmSchedStatus		= "";
	/** 상태 */
	private String pmSchedStatusDesc	= "";
	/** 작업ID */
	private String wkorId				= "";
	/** param */
	private String param				= "";

	
	public String getPmInsDListId()
    {
        return pmInsDListId;
    }
    public void setPmInsDListId(String pmInsDListId)
    {
        this.pmInsDListId = pmInsDListId;
    }
    public String getScheduleTypeDesc()
    {
        return scheduleTypeDesc;
    }
    public void setScheduleTypeDesc(String scheduleTypeDesc)
    {
        this.scheduleTypeDesc = scheduleTypeDesc;
    }
    public String getPeriodTypeDesc()
    {
        return periodTypeDesc;
    }
    public void setPeriodTypeDesc(String periodTypeDesc)
    {
        this.periodTypeDesc = periodTypeDesc;
    }
    public String getPmTypeDesc()
    {
        return pmTypeDesc;
    }
    public void setPmTypeDesc(String pmTypeDesc)
    {
        this.pmTypeDesc = pmTypeDesc;
    }
    public String getPmSchedStatusDesc()
    {
        return pmSchedStatusDesc;
    }
    public void setPmSchedStatusDesc(String pmSchedStatusDesc)
    {
        this.pmSchedStatusDesc = pmSchedStatusDesc;
    }
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
	public String getPmInsDSchedId()
    {
        return pmInsDSchedId;
    }
    public void setPmInsDSchedId(String pmInsDSchedId)
    {
        this.pmInsDSchedId = pmInsDSchedId;
        super.setAuditKey(pmInsDSchedId);
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
