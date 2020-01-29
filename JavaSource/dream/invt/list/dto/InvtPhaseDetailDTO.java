package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��  DTO
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class InvtPhaseDetailDTO extends BaseDTO
{
	/** ��� */
	private String remark = "";
	/** ���ڱݾ� */
	private String actualAmt = "";
	/** �������� */
	private String endDate = "";
	/** �������� */
	private String startDate = "";
	/** ������� */
	private String invtphaseStatus = "";
	
	private String invtphaseStatusDesc	= "";
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
	/** �������� ����Ʈ id */
	private String invtlistId = "";
	/** �������� �������� id */
	private String invtphaseId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
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
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(String actualAmt) {
		this.actualAmt = CommonUtil.convertMoney(actualAmt);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
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
	public String getInvtphaseStatusDesc() {
		return invtphaseStatusDesc;
	}
	public void setInvtphaseStatusDesc(String invtphaseStatusDesc) {
		this.invtphaseStatusDesc = invtphaseStatusDesc;
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
	public String getInvtlistId() {
		return invtlistId;
	}
	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}
	public String getInvtphaseId() {
		return invtphaseId;
	}
	public void setInvtphaseId(String invtphaseId) {
		this.invtphaseId = invtphaseId;
		super.setAuditKey(invtphaseId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

}