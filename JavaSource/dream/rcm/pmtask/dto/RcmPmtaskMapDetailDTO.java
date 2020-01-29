package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * ��  DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskMapDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmPmtaskMapDetailDTO extends BaseDTO
{
	/** ��� */
	private String remark = "";
	/** ���İ� */
	private String ordNo = "";
	/** �����[Y/N] */
	private String value = "";
	/** PM�۾������ε�� �׸� */
	private String description = "";
	/** PM TASK �� �׸� ID */
	private String pmtaskmapId = "";
	/** RCM FMEA ID */
	private String rcmfmeaId = "";
	/** RCM �ý��� �м� ����Ʈ id */
	private String rcmlistId = "";
	/** RCM����������� ID */
	private String rcmpmtaskId = "";
	/** RCM��������� ID */
	private String rcmpmtaskmapId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
	/** ����������� */
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