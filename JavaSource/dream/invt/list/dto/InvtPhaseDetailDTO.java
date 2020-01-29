package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세  DTO
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class InvtPhaseDetailDTO extends BaseDTO
{
	/** 비고 */
	private String remark = "";
	/** 투자금액 */
	private String actualAmt = "";
	/** 종료일자 */
	private String endDate = "";
	/** 시작일자 */
	private String startDate = "";
	/** 진행상태 */
	private String invtphaseStatus = "";
	
	private String invtphaseStatusDesc	= "";
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
	/** 설비투자 리스트 id */
	private String invtlistId = "";
	/** 설비투자 진행절차 id */
	private String invtphaseId = "";
	/** 회사코드 */
	private String compNo = "";
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