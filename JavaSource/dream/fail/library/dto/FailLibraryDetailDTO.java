package dream.fail.library.dto;

import common.bean.BaseDTO;

/**
 * ����Library - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class FailLibraryDetailDTO extends BaseDTO
{
	/** Ȯ�ο��� */
	private String checkYn = "";
	/** �߻����� */
	private String creDate = "";
	/** ��뿩�� */
	private String isUse = "";
	/** �����۾� NO */
	private String failurePmNo = "";
	
	private String failurePmDesc = "";
	/** ������ġ NO */
	private String failureReNo = "";
	
	private String failureReDesc = "";
	/** ������� NO */
	private String failureCaNo = "";
	
	private String failureCaDesc = "";
	/** �������� NO */
	private String failureMoNo = "";
	
	private String failureMoDesc = "";
	/** �����ڵ� NO */
	private String failurePtNo = "";
	
	private String failurePtDesc = "";
	/** ����ڵ� NO */
	private String eqctgNo = "";
	
	private String eqctgDesc = "";
	/** �����۾� ID */
	private String failurePmId = "";
	/** ������ġ ID */
	private String failureReId = "";
	/** ������� ID */
	private String failureCaId = "";
	/** �������� ID */
	private String failureMoId = "";
	/** �����ڵ� ID */
	private String failurePtId = "";
	/** ����ڵ�ID */
	private String eqctgId = "";
	/** ����SET ID */
	private String failsetlistId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getFailurePmNo() {
		return failurePmNo;
	}
	public void setFailurePmNo(String failurePmNo) {
		this.failurePmNo = failurePmNo;
	}
	public String getFailurePmDesc() {
		return failurePmDesc;
	}
	public void setFailurePmDesc(String failurePmDesc) {
		this.failurePmDesc = failurePmDesc;
	}
	public String getFailureReNo() {
		return failureReNo;
	}
	public void setFailureReNo(String failureReNo) {
		this.failureReNo = failureReNo;
	}
	public String getFailureReDesc() {
		return failureReDesc;
	}
	public void setFailureReDesc(String failureReDesc) {
		this.failureReDesc = failureReDesc;
	}
	public String getFailureCaNo() {
		return failureCaNo;
	}
	public void setFailureCaNo(String failureCaNo) {
		this.failureCaNo = failureCaNo;
	}
	public String getFailureCaDesc() {
		return failureCaDesc;
	}
	public void setFailureCaDesc(String failureCaDesc) {
		this.failureCaDesc = failureCaDesc;
	}
	public String getFailureMoNo() {
		return failureMoNo;
	}
	public void setFailureMoNo(String failureMoNo) {
		this.failureMoNo = failureMoNo;
	}
	public String getFailureMoDesc() {
		return failureMoDesc;
	}
	public void setFailureMoDesc(String failureMoDesc) {
		this.failureMoDesc = failureMoDesc;
	}
	public String getFailurePtNo() {
		return failurePtNo;
	}
	public void setFailurePtNo(String failurePtNo) {
		this.failurePtNo = failurePtNo;
	}
	public String getFailurePtDesc() {
		return failurePtDesc;
	}
	public void setFailurePtDesc(String failurePtDesc) {
		this.failurePtDesc = failurePtDesc;
	}
	public String getEqctgNo() {
		return eqctgNo;
	}
	public void setEqctgNo(String eqctgNo) {
		this.eqctgNo = eqctgNo;
	}
	public String getEqctgDesc() {
		return eqctgDesc;
	}
	public void setEqctgDesc(String eqctgDesc) {
		this.eqctgDesc = eqctgDesc;
	}
	public String getFailurePmId() {
		return failurePmId;
	}
	public void setFailurePmId(String failurePmId) {
		this.failurePmId = failurePmId;
	}
	public String getFailureReId() {
		return failureReId;
	}
	public void setFailureReId(String failureReId) {
		this.failureReId = failureReId;
	}
	public String getFailureCaId() {
		return failureCaId;
	}
	public void setFailureCaId(String failureCaId) {
		this.failureCaId = failureCaId;
	}
	public String getFailureMoId() {
		return failureMoId;
	}
	public void setFailureMoId(String failureMoId) {
		this.failureMoId = failureMoId;
	}
	public String getFailurePtId() {
		return failurePtId;
	}
	public void setFailurePtId(String failurePtId) {
		this.failurePtId = failurePtId;
	}
	public String getEqctgId() {
		return eqctgId;
	}
	public void setEqctgId(String eqctgId) {
		this.eqctgId = eqctgId;
	}
	public String getFailsetlistId() {
		return failsetlistId;
	}
	public void setFailsetlistId(String failsetlistId) {
		this.failsetlistId = failsetlistId;
		super.setAuditKey(failsetlistId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
}
