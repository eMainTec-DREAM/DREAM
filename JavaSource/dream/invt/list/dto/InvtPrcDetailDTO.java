package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  kim21017
 * @version $Id: RcmFmeaDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class InvtPrcDetailDTO extends BaseDTO
{
	/** 비고[주의사항] */
	private String remark = "";
	/** 실제완료일자 */
	private String endDate = "";
	/** 완료시기 */
	private String planEdate = "";
	/** 투자시기 */
	private String planSdate = "";
	/** 예상금액 */
	private String planAmt = "";
	/** 투자금액 */
	private String invtAmt	= "";
	/** 담당자 */
	private String empId = "";
	
	private String empDesc	= "";
	/** 부서 */
	private String deptId = "";
	
	private String deptDesc	= "";
	/** 설비ID */
	private String equipId = "";
	
	private String equipDesc	= "";
	/** 투자분류정보 */
	private String invtCateg = "";
	
	private String invtCategDesc	= "";
	/** 기능코드ID */
	private String eqctgId = "";
	
	private String eqctgDesc	= "";
	/** 위치분류ID */
	private String eqlocId = "";
	
	private String eqlocDesc	= "";
	/** 구매절차 종류ID */
	private String invtprctpId = "";
	/** 설비투자 상태 */
	private String invtlistStatus = "";
	
	private String invtlistStatusDesc	= "";
	/** 설비투자 명 */
	private String description = "";
	/** 설비투자 리스트 코드 */
	private String invtlistNo = "";
	/** 설비투자 리스트 id */
	private String invtlistId = "";
	/** 회사코드 */
	private String compNo = "";
	/** 진행단계 */
	private String invtphaseStatusDesc	= "";
	/** 구매절차명 */
	private String invtDesc	= "";
	/** 상태 */
	private String invtStatusDesc = "";

	private String eqLocDesc	= "";
	/** 투자금액 */
	private String actualAmt = "";
	/** 시작일자 */
	private String startDate = "";
	/** 진행상태 */
	private String invtphaseStatus = "";
	/** 관련문서 */
	private String refDoc = "";
	/** 관련부서 */
	private String refDepart = "";
	
	private String refDepartDesc	= "";
	/** 구매절차 소분류 */
	private String invtProcStype = "";
	
	private String invtProcStypeDesc	= "";
	/** 구매절차 대분류 */
	private String invtProcLtype = "";
	
	private String invtProcLtypeDesc	= "";
	/** 진행순서 */
	private String ordNo = "";
	/** 구매절차단계 ID */
	private String invtprcphId = "";
	/** 설비투자 진행절차 id */
	private String invtphaseId = "";
	/** 문서번호 */
	private String invtDocNo = "";
	
    public String getInvtDocNo()
    {
        return invtDocNo;
    }
    public void setInvtDocNo(String invtDocNo)
    {
        this.invtDocNo = invtDocNo;
    }
    public String getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(String actualAmt) {
		this.actualAmt = actualAmt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getInvtphaseStatus() {
		return invtphaseStatus;
	}
	public void setInvtphaseStatus(String invtphaseStatus) {
		this.invtphaseStatus = invtphaseStatus;
	}
	public String getRefDoc() {
		return refDoc;
	}
	public void setRefDoc(String refDoc) {
		this.refDoc = refDoc;
	}
	public String getRefDepart() {
		return refDepart;
	}
	public void setRefDepart(String refDepart) {
		this.refDepart = refDepart;
	}
	public String getRefDepartDesc() {
		return refDepartDesc;
	}
	public void setRefDepartDesc(String refDepartDesc) {
		this.refDepartDesc = refDepartDesc;
	}
	public String getInvtProcStype() {
		return invtProcStype;
	}
	public void setInvtProcStype(String invtProcStype) {
		this.invtProcStype = invtProcStype;
	}
	public String getInvtProcStypeDesc() {
		return invtProcStypeDesc;
	}
	public void setInvtProcStypeDesc(String invtProcStypeDesc) {
		this.invtProcStypeDesc = invtProcStypeDesc;
	}
	public String getInvtProcLtype() {
		return invtProcLtype;
	}
	public void setInvtProcLtype(String invtProcLtype) {
		this.invtProcLtype = invtProcLtype;
	}
	public String getInvtProcLtypeDesc() {
		return invtProcLtypeDesc;
	}
	public void setInvtProcLtypeDesc(String invtProcLtypeDesc) {
		this.invtProcLtypeDesc = invtProcLtypeDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getInvtprcphId() {
		return invtprcphId;
	}
	public void setInvtprcphId(String invtprcphId) {
		this.invtprcphId = invtprcphId;
	}
	public String getInvtphaseId() {
		return invtphaseId;
	}
	public void setInvtphaseId(String invtphaseId) {
		this.invtphaseId = invtphaseId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getInvtStatusDesc() {
		return invtStatusDesc;
	}
	public void setInvtStatusDesc(String invtStatusDesc) {
		this.invtStatusDesc = invtStatusDesc;
	}
	public String getInvtDesc() {
		return invtDesc;
	}
	public void setInvtDesc(String invtDesc) {
		this.invtDesc = invtDesc;
	}
	public String getInvtphaseStatusDesc() {
		return invtphaseStatusDesc;
	}
	public void setInvtphaseStatusDesc(String invtphaseStatusDesc) {
		this.invtphaseStatusDesc = invtphaseStatusDesc;
	}
	public String getInvtAmt() {
		return invtAmt;
	}
	public void setInvtAmt(String invtAmt) {
		this.invtAmt = CommonUtil.convertMoney(invtAmt);
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getInvtCategDesc() {
		return invtCategDesc;
	}
	public void setInvtCategDesc(String invtCategDesc) {
		this.invtCategDesc = invtCategDesc;
	}
	public String getEqctgDesc() {
		return eqctgDesc;
	}
	public void setEqctgDesc(String eqctgDesc) {
		this.eqctgDesc = eqctgDesc;
	}
	public String getEqlocDesc() {
		return eqlocDesc;
	}
	public void setEqlocDesc(String eqlocDesc) {
		this.eqlocDesc = eqlocDesc;
	}
	public String getInvtlistStatusDesc() {
		return invtlistStatusDesc;
	}
	public void setInvtlistStatusDesc(String invtlistStatusDesc) {
		this.invtlistStatusDesc = invtlistStatusDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getPlanEdate() {
		return planEdate;
	}
	public void setPlanEdate(String planEdate) {
		this.planEdate = CommonUtil.convertDate(planEdate);
	}
	public String getPlanSdate() {
		return planSdate;
	}
	public void setPlanSdate(String planSdate) {
		this.planSdate = CommonUtil.convertDate(planSdate);
	}
	public String getPlanAmt() {
		return planAmt;
	}
	public void setPlanAmt(String planAmt) {
		this.planAmt = CommonUtil.convertMoney(planAmt);
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getInvtCateg() {
		return invtCateg;
	}
	public void setInvtCateg(String invtCateg) {
		this.invtCateg = invtCateg;
	}
	public String getEqctgId() {
		return eqctgId;
	}
	public void setEqctgId(String eqctgId) {
		this.eqctgId = eqctgId;
	}
	public String getEqlocId() {
		return eqlocId;
	}
	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}
	public String getInvtprctpId() {
		return invtprctpId;
	}
	public void setInvtprctpId(String invtprctpId) {
		this.invtprctpId = invtprctpId;
		super.setAuditKey(invtprctpId);
	}
	public String getInvtlistStatus() {
		return invtlistStatus;
	}
	public void setInvtlistStatus(String invtlistStatus) {
		this.invtlistStatus = invtlistStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInvtlistNo() {
		return invtlistNo;
	}
	public void setInvtlistNo(String invtlistNo) {
		this.invtlistNo = invtlistNo;
	}
	public String getInvtlistId() {
		return invtlistId;
	}
	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
}
