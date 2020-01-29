package dream.work.let.permit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 안전작업허가서 작업유형 - 작업자 목록 DTO
 * @author  syyang
 * @version $Id: WorkLetPermitCraftDetailDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class WorkLetPermitCraftDetailDTO extends BaseDTO
{
	/** 안전작업허가서 작업자 ID */
	private String woLetListCraftId 	= "";
	
	/** 안전작업허가서ID */
	private String woLetListId 			= "";

	/** 순서 */
	private String stepNum 				= "";
	/** 내부/외부 작업자구분 */
	private String craftType 			= "";
	/** 내부/외부 작업자구분 */
	private String craftTypeDesc		= "";
	/** 작업자명 */
	private String workName				= "";
	
	/** 안전작업허가기간 FROM 작업일자 */
	private String startDate			= "";
	/** 안전작업허가기간 FROM작업시간 */
	private String startTime			= "";
	/** 안전작업허가기간 TO작업일자 */
	private String endDate				= "";
	/** 안전작업허가기간 TO작업시간 */
	private String endTime				= "";
	
	/** 작업시간(분) */
	private String workTime 			= "";
	/** 비고 */
	private String remark 				= "";
	
	/** 최초생성시간 */
	private String creTime				= "";
	/** 마지막수정일자 */
	private String updTime				= "";
	
	public String getWoLetListCraftId() {
		return woLetListCraftId;
	}
	public void setWoLetListCraftId(String woLetListCraftId) {
		this.woLetListCraftId = woLetListCraftId;
		super.setAuditKey(woLetListCraftId);
	}
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = CommonUtil.convertMoney(stepNum);
	}
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getCraftTypeDesc() {
		return craftTypeDesc;
	}
	public void setCraftTypeDesc(String craftTypeDesc) {
		this.craftTypeDesc = craftTypeDesc;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime); 
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = CommonUtil.convertDateTime(creTime);
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = CommonUtil.convertDateTime(updTime);
	}

	
}