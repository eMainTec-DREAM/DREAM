package dream.tool.adj.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���ⱸ��� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPttDisDetailDTO extends BaseDTO
{
	/**  */
    private String ptdisuselistId = "";
    /** ���Ϸ���� */
    private String ptDisStatus = "";
    /** �����¸� */
    private String ptDisStatusDesc  = "";
    /** ���� */
    private String description        = "";
    /** ������� */
    private String disDate        = "";
	/** â��ID */
	private String wcodeId         = "";
	/** â��� */
	private String wname           = "";
    /** ����μ� */
    private String exeDept = "";
    /** ����μ� */
    private String exeDeptDesc = "";
    /** ������ */
    private String exeBy = "";
    /** ������ */
    private String exeByName = "";
    /** ��� */
    private String remark = "";
	public String getPtdisuselistId() {
		return ptdisuselistId;
	}
	public void setPtdisuselistId(String ptdisuselistId) {
		this.ptdisuselistId = ptdisuselistId;
		super.setAuditKey(ptdisuselistId);
	}
	public String getPtDisStatus() {
		return ptDisStatus;
	}
	public void setPtDisStatus(String ptDisStatus) {
		this.ptDisStatus = ptDisStatus;
	}
	public String getPtDisStatusDesc() {
		return ptDisStatusDesc;
	}
	public void setPtDisStatusDesc(String ptDisStatusDesc) {
		this.ptDisStatusDesc = ptDisStatusDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisDate() {
		return disDate;
	}
	public void setDisDate(String disDate) {
		this.disDate = CommonUtil.convertDate(disDate);
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
	public String getExeDept() {
		return exeDept;
	}
	public void setExeDept(String exeDept) {
		this.exeDept = exeDept;
	}
	public String getExeDeptDesc() {
		return exeDeptDesc;
	}
	public void setExeDeptDesc(String exeDeptDesc) {
		this.exeDeptDesc = exeDeptDesc;
	}
	public String getExeBy() {
		return exeBy;
	}
	public void setExeBy(String exeBy) {
		this.exeBy = exeBy;
	}
	public String getExeByName() {
		return exeByName;
	}
	public void setExeByName(String exeByName) {
		this.exeByName = exeByName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    

}