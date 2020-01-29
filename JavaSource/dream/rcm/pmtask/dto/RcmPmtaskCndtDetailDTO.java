package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * ��  DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmPmtaskCndtDetailDTO extends BaseDTO
{
	/** ����۾����⵵ */
	private String taskefinfo = "";
	/** PM�۾�����۾� �׸� */
	private String taskcndt = "";
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
	/** ȸ���ڵ� */
	private String compNo = "";
	/** ����������� */
	private String rsltTask = "";
	/** �׸��ڵ� */
	private String pmtaskmapNo = "";
	
	private String rcmpmtaskcndtId	= "";
	
	
	public String getTaskefinfo() {
		return taskefinfo;
	}
	public void setTaskefinfo(String taskefinfo) {
		this.taskefinfo = taskefinfo;
	}
	public String getTaskcndt() {
		return taskcndt;
	}
	public void setTaskcndt(String taskcndt) {
		this.taskcndt = taskcndt;
	}
	public String getRcmpmtaskcndtId() {
		return rcmpmtaskcndtId;
	}
	public void setRcmpmtaskcndtId(String rcmpmtaskcndtId) {
		this.rcmpmtaskcndtId = rcmpmtaskcndtId;
		super.setAuditKey(rcmpmtaskcndtId);
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
	public String getPmtaskmapNo() {
		return pmtaskmapNo;
	}
	public void setPmtaskmapNo(String pmtaskmapNo) {
		this.pmtaskmapNo = pmtaskmapNo;
	}
}