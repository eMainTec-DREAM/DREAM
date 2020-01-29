package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 작업시간 상세 DTO
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public class WorkPmListMsTimeDetailDTO extends BaseDTO
{
	/** 작업시간 ID */
	private String pmMsTimeId		= "";
	
    /** 작업시간 */
    private String measureTime		= "";
    /** 작업시간 ID */
    private String measureTimeId	= "";
    
    /** 비고 */
    private String remark			= "";
    
    /** MultiSelect MsTime ID */
    private String multiMsTimeKey     = "";
    /** MultiSelect MsTime DESC */
    private String multiMsTimeDesc    = "";
    
	public String getMultiMsTimeKey() {
		return multiMsTimeKey;
	}
	public void setMultiMsTimeKey(String multiMsTimeKey) {
		this.multiMsTimeKey = multiMsTimeKey;
	}
	public String getMultiMsTimeDesc() {
		return multiMsTimeDesc;
	}
	public void setMultiMsTimeDesc(String multiMsTimeDesc) {
		this.multiMsTimeDesc = multiMsTimeDesc;
	}
	public String getMeasureTimeId() {
		return measureTimeId;
	}
	public void setMeasureTimeId(String measureTimeId) {
		this.measureTimeId = measureTimeId;
	}
	public String getPmMsTimeId() {
		return pmMsTimeId;
	}
	public void setPmMsTimeId(String pmMsTimeId) {
		this.pmMsTimeId = pmMsTimeId;
		super.setAuditKey(pmMsTimeId);
	}
	public String getMeasureTime() {
		return measureTime;
	}
	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}