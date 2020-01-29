package dream.tool.iss.rtn.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����ݳ�Ȯ�� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttRtnDetailDTO extends BaseDTO
{
	/**  */
    private String ptRtnListId = "";
    /** �ݳ��Ϸ���� */
    private String ptRtnStatus = "";
    /** �ݳ����¸� */
    private String ptRtnStatusDesc  = "";
    /** �ݳ����� */
    private String rtnDate        = "";
	/** â��ID */
	private String wcodeId         = "";
	/** â��� */
	private String wname           = "";
    /** ��ǰ��/�԰� */
    private String partDesc         = "";
	/** ����Id */
	private String partId          = "";
	/** �����ȣ */
	private String partNo          = "";
    /** ���μ� */
    private String rtnDept = "";
    /** ���μ� */
    private String rtnDeptDesc = "";
    /** ������ */
    private String rtnQty = "";
    /** ������ */
    private String recBy = "";
    /** ������ */
    private String recByName = "";
    /** ���ɺμ� */
    private String recDept = "";
    /** ���ɺμ� */
    private String recDeptDesc = "";
    /** ��� */
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