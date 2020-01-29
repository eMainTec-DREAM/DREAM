package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  kim21017
 * @version $Id: RcmFmeaDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class InvtPrcDetailDTO extends BaseDTO
{
	/** ���[���ǻ���] */
	private String remark = "";
	/** �����Ϸ����� */
	private String endDate = "";
	/** �Ϸ�ñ� */
	private String planEdate = "";
	/** ���ڽñ� */
	private String planSdate = "";
	/** ����ݾ� */
	private String planAmt = "";
	/** ���ڱݾ� */
	private String invtAmt	= "";
	/** ����� */
	private String empId = "";
	
	private String empDesc	= "";
	/** �μ� */
	private String deptId = "";
	
	private String deptDesc	= "";
	/** ����ID */
	private String equipId = "";
	
	private String equipDesc	= "";
	/** ���ںз����� */
	private String invtCateg = "";
	
	private String invtCategDesc	= "";
	/** ����ڵ�ID */
	private String eqctgId = "";
	
	private String eqctgDesc	= "";
	/** ��ġ�з�ID */
	private String eqlocId = "";
	
	private String eqlocDesc	= "";
	/** �������� ����ID */
	private String invtprctpId = "";
	/** �������� ���� */
	private String invtlistStatus = "";
	
	private String invtlistStatusDesc	= "";
	/** �������� �� */
	private String description = "";
	/** �������� ����Ʈ �ڵ� */
	private String invtlistNo = "";
	/** �������� ����Ʈ id */
	private String invtlistId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
	/** ����ܰ� */
	private String invtphaseStatusDesc	= "";
	/** ���������� */
	private String invtDesc	= "";
	/** ���� */
	private String invtStatusDesc = "";

	private String eqLocDesc	= "";
	/** ���ڱݾ� */
	private String actualAmt = "";
	/** �������� */
	private String startDate = "";
	/** ������� */
	private String invtphaseStatus = "";
	/** ���ù��� */
	private String refDoc = "";
	/** ���úμ� */
	private String refDepart = "";
	
	private String refDepartDesc	= "";
	/** �������� �Һз� */
	private String invtProcStype = "";
	
	private String invtProcStypeDesc	= "";
	/** �������� ��з� */
	private String invtProcLtype = "";
	
	private String invtProcLtypeDesc	= "";
	/** ������� */
	private String ordNo = "";
	/** ���������ܰ� ID */
	private String invtprcphId = "";
	/** �������� �������� id */
	private String invtphaseId = "";
	/** ������ȣ */
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
