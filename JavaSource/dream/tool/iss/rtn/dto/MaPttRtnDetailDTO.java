package dream.tool.iss.rtn.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재반납확정 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttRtnDetailDTO extends BaseDTO
{
	/**  */
    private String ptRtnListId = "";
    /** 반납완료상태 */
    private String ptRtnStatus = "";
    /** 반납상태명 */
    private String ptRtnStatusDesc  = "";
    /** 반납일자 */
    private String rtnDate        = "";
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
    private String rtnDept = "";
    /** 출고부서 */
    private String rtnDeptDesc = "";
    /** 사용수량 */
    private String rtnQty = "";
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
	public String getPtRtnListId() {
		return ptRtnListId;
	}
	public void setPtRtnListId(String ptRtnListId) {
		this.ptRtnListId = ptRtnListId;
		super.setAuditKey(ptRtnListId);
	}
	public String getPtRtnStatus() {
		return ptRtnStatus;
	}
	public void setPtRtnStatus(String ptRtnStatus) {
		this.ptRtnStatus = ptRtnStatus;
	}
	public String getPtRtnStatusDesc() {
		return ptRtnStatusDesc;
	}
	public void setPtRtnStatusDesc(String ptRtnStatusDesc) {
		this.ptRtnStatusDesc = ptRtnStatusDesc;
	}
	public String getRtnDate() {
		return rtnDate;
	}
	public void setRtnDate(String rtnDate) {
		this.rtnDate = CommonUtil.convertDate(rtnDate);
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
	public String getRtnDept() {
		return rtnDept;
	}
	public void setRtnDept(String rtnDept) {
		this.rtnDept = rtnDept;
	}
	public String getRtnDeptDesc() {
		return rtnDeptDesc;
	}
	public void setRtnDeptDesc(String rtnDeptDesc) {
		this.rtnDeptDesc = rtnDeptDesc;
	}
	public String getRtnQty() {
		return rtnQty;
	}
	public void setRtnQty(String rtnQty) {
		this.rtnQty = rtnQty;
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