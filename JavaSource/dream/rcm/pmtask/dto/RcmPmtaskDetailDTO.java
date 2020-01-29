package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * 상세 DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class RcmPmtaskDetailDTO extends BaseDTO
{
	private String rcmpmtaskId	= "";
	/**  */
	private String remark = "";
	/**  */
	private String effailDesc = "";
	/**  */
	private String effcd = "";
	/**  */
	private String effailId = "";
	/**  */
	private String cafailDesc = "";
	/**  */
	private String cafcd = "";
	/**  */
	private String cafailId = "";
	/**  */
	private String mofailDesc = "";
	/**  */
	private String mofcd = "";
	/**  */
	private String mofailId = "";
	/**  */
	private String rcmeqasmbId = "";
	/**  */
	private String rcmeqId = "";
	/**  */
	private String rcmffailId = "";
	/**  */
	private String rcmfuncId = "";
	/**  */
	private String rcmlistId = "";
	
	private String rcmlistDesc = "";
	/**  */
	private String rcmfmeaId = "";
	/**  */
	private String compNo = "";
	/** 설비 */
	private String equipDesc	= "";
	/** 기능 */
	private String funcDesc	 = "";
	/** 기능고장 */
	private String failDesc		= "";
	/** 부위 */
	private String asmbDesc	= "";
	
	private String pmDesc		= "";
	
	private String rcmTaskTypeDesc = "";
	
	private String rcmTaskType		= "";
	
	private String cycle		= "";
	
	private String periodType	= "";
	
	private String periodTypeDesc	= "";
	
	
	
	public String getRcmpmtaskId() {
		return rcmpmtaskId;
	}
	public void setRcmpmtaskId(String rcmpmtaskId) {
		this.rcmpmtaskId = rcmpmtaskId;
		super.setAuditKey(rcmpmtaskId);
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getPmDesc() {
		return pmDesc;
	}
	public void setPmDesc(String pmDesc) {
		this.pmDesc = pmDesc;
	}
	public String getRcmTaskTypeDesc() {
		return rcmTaskTypeDesc;
	}
	public void setRcmTaskTypeDesc(String rcmTaskTypeDesc) {
		this.rcmTaskTypeDesc = rcmTaskTypeDesc;
	}
	public String getRcmTaskType() {
		return rcmTaskType;
	}
	public void setRcmTaskType(String rcmTaskType) {
		this.rcmTaskType = rcmTaskType;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEffailDesc() {
		return effailDesc;
	}
	public void setEffailDesc(String effailDesc) {
		this.effailDesc = effailDesc;
	}
	public String getEffcd() {
		return effcd;
	}
	public void setEffcd(String effcd) {
		this.effcd = effcd;
	}
	public String getEffailId() {
		return effailId;
	}
	public void setEffailId(String effailId) {
		this.effailId = effailId;
	}
	public String getCafailDesc() {
		return cafailDesc;
	}
	public void setCafailDesc(String cafailDesc) {
		this.cafailDesc = cafailDesc;
	}
	public String getCafcd() {
		return cafcd;
	}
	public void setCafcd(String cafcd) {
		this.cafcd = cafcd;
	}
	public String getCafailId() {
		return cafailId;
	}
	public void setCafailId(String cafailId) {
		this.cafailId = cafailId;
	}
	public String getMofailDesc() {
		return mofailDesc;
	}
	public void setMofailDesc(String mofailDesc) {
		this.mofailDesc = mofailDesc;
	}
	public String getMofcd() {
		return mofcd;
	}
	public void setMofcd(String mofcd) {
		this.mofcd = mofcd;
	}
	public String getMofailId() {
		return mofailId;
	}
	public void setMofailId(String mofailId) {
		this.mofailId = mofailId;
	}
	public String getRcmeqasmbId() {
		return rcmeqasmbId;
	}
	public void setRcmeqasmbId(String rcmeqasmbId) {
		this.rcmeqasmbId = rcmeqasmbId;
	}
	public String getRcmeqId() {
		return rcmeqId;
	}
	public void setRcmeqId(String rcmeqId) {
		this.rcmeqId = rcmeqId;
	}
	public String getRcmffailId() {
		return rcmffailId;
	}
	public void setRcmffailId(String rcmffailId) {
		this.rcmffailId = rcmffailId;
	}
	public String getRcmfuncId() {
		return rcmfuncId;
	}
	public void setRcmfuncId(String rcmfuncId) {
		this.rcmfuncId = rcmfuncId;
	}
	public String getRcmlistId() {
		return rcmlistId;
	}
	public void setRcmlistId(String rcmlistId) {
		this.rcmlistId = rcmlistId;
	}
	public String getRcmlistDesc() {
		return rcmlistDesc;
	}
	public void setRcmlistDesc(String rcmlistDesc) {
		this.rcmlistDesc = rcmlistDesc;
	}
	public String getRcmfmeaId() {
		return rcmfmeaId;
	}
	public void setRcmfmeaId(String rcmfmeaId) {
		this.rcmfmeaId = rcmfmeaId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	public String getFailDesc() {
		return failDesc;
	}
	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
	}
	public String getAsmbDesc() {
		return asmbDesc;
	}
	public void setAsmbDesc(String asmbDesc) {
		this.asmbDesc = asmbDesc;
	}

}
