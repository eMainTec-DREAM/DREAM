package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * 상세  DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskMapDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmPmtaskMapDetailDTO extends BaseDTO
{
	/** 비고 */
	private String remark = "";
	/** 정렬값 */
	private String ordNo = "";
	/** 결과값[Y/N] */
	private String value = "";
	/** PM작업설정로드맵 항목 */
	private String description = "";
	/** PM TASK 맵 항목 ID */
	private String pmtaskmapId = "";
	/** RCM FMEA ID */
	private String rcmfmeaId = "";
	/** RCM 시스템 분석 리스트 id */
	private String rcmlistId = "";
	/** RCM예방업무설정 ID */
	private String rcmpmtaskId = "";
	/** RCM예방업무맵 ID */
	private String rcmpmtaskmapId = "";
	/** 회사코드 */
	private String compNo = "";
	/** 결과업무형태 */
	private String rsltTask = "";

	private String rcmpmtaskcndtId = "";
	
	
	public String getRcmpmtaskcndtId() {
		return rcmpmtaskcndtId;
	}
	public void setRcmpmtaskcndtId(String rcmpmtaskcndtId) {
		this.rcmpmtaskcndtId = rcmpmtaskcndtId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPmtaskmapId() {
		return pmtaskmapId;
	}
	public void setPmtaskmapId(String pmtaskmapId) {
		this.pmtaskmapId = pmtaskmapId;
	}
	public String getRcmfmeaId() {
		return rcmfmeaId;
	}
	public void setRcmfmeaId(String rcmfmeaId) {
		this.rcmfmeaId = rcmfmeaId;
	}
	public String getRcmlistId() {
		return rcmlistId;
	}
	public void setRcmlistId(String rcmlistId) {
		this.rcmlistId = rcmlistId;
	}
	public String getRcmpmtaskId() {
		return rcmpmtaskId;
	}
	public void setRcmpmtaskId(String rcmpmtaskId) {
		this.rcmpmtaskId = rcmpmtaskId;
	}
	public String getRcmpmtaskmapId() {
		return rcmpmtaskmapId;
	}
	public void setRcmpmtaskmapId(String rcmpmtaskmapId) {
		this.rcmpmtaskmapId = rcmpmtaskmapId;
		super.setAuditKey(rcmpmtaskmapId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getRsltTask() {
		return rsltTask;
	}
	public void setRsltTask(String rsltTask) {
		this.rsltTask = rsltTask;
	}
}