package dream.fail.library.dto;

import common.bean.BaseDTO;

/**
 * 고장Library - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class FailLibraryDetailDTO extends BaseDTO
{
	/** 확인여부 */
	private String checkYn = "";
	/** 발생일자 */
	private String creDate = "";
	/** 사용여부 */
	private String isUse = "";
	/** 예방작업 NO */
	private String failurePmNo = "";
	
	private String failurePmDesc = "";
	/** 고장조치 NO */
	private String failureReNo = "";
	
	private String failureReDesc = "";
	/** 고장원인 NO */
	private String failureCaNo = "";
	
	private String failureCaDesc = "";
	/** 고장현상 NO */
	private String failureMoNo = "";
	
	private String failureMoDesc = "";
	/** 부위코드 NO */
	private String failurePtNo = "";
	
	private String failurePtDesc = "";
	/** 기능코드 NO */
	private String eqctgNo = "";
	
	private String eqctgDesc = "";
	/** 예방작업 ID */
	private String failurePmId = "";
	/** 고장조치 ID */
	private String failureReId = "";
	/** 고장원인 ID */
	private String failureCaId = "";
	/** 고장현상 ID */
	private String failureMoId = "";
	/** 부위코드 ID */
	private String failurePtId = "";
	/** 기능코드ID */
	private String eqctgId = "";
	/** 고장SET ID */
	private String failsetlistId = "";
	/** 회사코드 */
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
