package dream.work.cal.pmrinsperiod.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾�����(�Ⱓ) - �� DTO
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class WorkCalPmRInsPeriodDetailDTO extends BaseDTO
{
	/** ������ID */
	private String pmInsSchedId 		= "";
	/** ���ID */
	private String pmInsListId 			= "";
	/** PM ID */
	private String pmId					= "";
	/** PM��ȣ */
	private String pmNo 				= "";
	/** ���� */
	private String equipDesc	 		= "";
	/** ��ȹ���� */
	private String planDate	 			= "";
	/** PM�� */
	private String pmDesc 				= "";
	/** ������/�ð� ���� */
	private String scheduleType	 		= "";
	/** ������/�ð� ���� */
	private String scheduleTypeDesc		= "";
	/** �μ��� */
	private String deptDesc	 			= "";
	/** �ֱ� */
	private String cycle	 			= "";
	/** ������(�ð�) */
	private String usage	 			= "";
	/** �ֱⱸ��(��/��/��) */
	private String periodType	 		= "";
	/** �ֱⱸ��(��/��/��) */
	private String periodTypeDesc 		= "";
	/** �۾����� */
	private String pmType	 			= "";
	/** �۾����� */
	private String pmTypeDesc	 		= "";
	/** ��� */
	private String remark	 			= "";
	/** �������� */
	private String schedDate 			= "";
	/** ���� */
	private String pmSchedStatus		= "";
	/** �۾�ID */
	private String wkorId				= "";
	
	/** ����� Desc */
	private String empDesc				= "";
	
	/** �����ð� */
	private String measureTime          = "";
	/** param */
	private String param				= "";

    public String getPeriodTypeDesc()
    {
        return periodTypeDesc;
    }
    public void setPeriodTypeDesc(String periodTypeDesc)
    {
        this.periodTypeDesc = periodTypeDesc;
    }
    public String getScheduleTypeDesc()
    {
        return scheduleTypeDesc;
    }
    public void setScheduleTypeDesc(String scheduleTypeDesc)
    {
        this.scheduleTypeDesc = scheduleTypeDesc;
    }
    public String getPmTypeDesc()
    {
        return pmTypeDesc;
    }
    public void setPmTypeDesc(String pmTypeDesc)
    {
        this.pmTypeDesc = pmTypeDesc;
    }
    public String getPmInsListId()
    {
        return pmInsListId;
    }
    public void setPmInsListId(String pmInsListId)
    {
        this.pmInsListId = pmInsListId;
    }
    public String getMeasureTime()
    {
        return measureTime;
    }
    public void setMeasureTime(String measureTime)
    {
        this.measureTime = measureTime;
    }
    public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
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
	public String getPmInsSchedId() {
		return pmInsSchedId;
	}
	public void setPmInsSchedId(String pmInsSchedId) {
		this.pmInsSchedId = pmInsSchedId;
		super.setAuditKey(pmInsSchedId);
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
