package dream.tool.iss.rent.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재출고확정 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttIssDetailDTO extends BaseDTO
{
	/**  */
    private String ptIssListId = "";
    /** 출고완료상태[미출고,출고완료] */
    private String ptissStatus = "";
    /** 출고상태명 */
    private String ptissStatusDesc  = "";
    /** 출고일자 */
    private String issueDate        = "";
	/** 창고ID */
	private String wcodeId         = "";
	/** 창고명 */
	private String wname           = "";
    /** 부품명/규격 */
    private String partDesc         = "";
	/** 자재Id */
	private String partId          = "";
	/** 자재번호 */
	private String partNo          = "";
    /** 출고부서 */
    private String issueDept = "";
    /** 출고부서 */
    private String issueDeptDesc = "";
    /** 사용수량 */
    private String issueQty = "";
    /** 수령자 */
    private String recBy = "";
    /** 수령자 */
    private String recByName = "";
    /** 수령부서 */
    private String recDept = "";
    /** 수령부서 */
    private String recDeptDesc = "";
    /** 비고 */
    private String remark    = "";
	
	
	public String getPtIssListId() {
		return ptIssListId;
	}
	public void setPtIssListId(String ptIssListId) {
		this.ptIssListId = ptIssListId;
		super.setAuditKey(ptIssListId);
	}
	public String getPtissStatus() {
		return ptissStatus;
	}
	public void setPtissStatus(String ptissStatus) {
		this.ptissStatus = ptissStatus;
	}
	public String getPtissStatusDesc() {
		return ptissStatusDesc;
	}
	public void setPtissStatusDesc(String ptissStatusDesc) {
		this.ptissStatusDesc = ptissStatusDesc;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = CommonUtil.convertDate(issueDate);
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getIssueDept() {
		return issueDept;
	}
	public void setIssueDept(String issueDept) {
		this.issueDept = issueDept;
	}
	public String getIssueDeptDesc() {
		return issueDeptDesc;
	}
	public void setIssueDeptDesc(String issueDeptDesc) {
		this.issueDeptDesc = issueDeptDesc;
	}
	public String getIssueQty() {
		return issueQty;
	}
	public void setIssueQty(String issueQty) {
		this.issueQty = issueQty;
	}
	public String getRecBy() {
		return recBy;
	}
	public void setRecBy(String recBy) {
		this.recBy = recBy;
	}
	public String getRecByName() {
		return recByName;
	}
	public void setRecByName(String recByName) {
		this.recByName = recByName;
	}
	public String getRecDept() {
		return recDept;
	}
	public void setRecDept(String recDept) {
		this.recDept = recDept;
	}
	public String getRecDeptDesc() {
		return recDeptDesc;
	}
	public void setRecDeptDesc(String recDeptDesc) {
		this.recDeptDesc = recDeptDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
}