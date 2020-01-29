package dream.part.iss.emg.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtIssEmgDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo = "";
	/** ������ID */
	private String ptemgisslistId = "";
	/** ������Ϸ����[�����,���Ϸ�] */
	private String ptemgissStatus = "";
	/** ������Ϸ���¸�  */
	private String ptemgissStatusDesc	= "";
	
	/** ����� */
	private String ptissType = "";
	
	private String ptissTypeDesc = "";
	/** �۾��������[W:������,C:����Ϸ�] */
	private String ptemgTaskStatus = "";
	
	private String ptemgTaskStatusDesc	="";
	/** �۾�����ID */
	private String wopartId = "";
	/** �۾�ID */
	private String wkorId = "";
	/** ���â�� */
	private String wcodeId = "";
	/**  */
	private String wname = "";
	/** ���������� */
	private String issueDate = "";
	/** ������μ� */
	private String issueDept = "";
	
	private String issueDeptDesc = "";
	/** �������� */
	private String issueBy = "";
	
	private String issueByName = "";
	/** ������ */
	private String recBy = "";
	
	private String recByName = "";
	/** ���ǰ�� */
	private String partId = "";
	/** ������� */
	private String partGrade = "";
	
	private String partGradeDesc = "";
	/** ��û���� */
    private String reqQty = "";
	/** ������ */
	private String issueQty = "";
	/** ����� */
	private String stockQty = "";
	/** ��� */
	private String remark = "";
	
	private String partNo	= "";
	
	private String partDesc	= "";
	
	private String woDesc	= "";
	/** ����� ���� id */
	private String equipId = "";
	/** ����� ����� */
	private String equipName = "";
	/** ����� ������� id */
	private String eqAsmbId = "";
	/** ����� ��������� */
    private String eqAsmbDesc = "";
    /** ����� ������ġ�� */
    private String eqLocDesc = "";
    /** ����û id */
    private String ptemgissreqId = "";
    /** �ڽ�Ʈ���� id */
    private String ctCtrId 		= "";
    /** �ڽ�Ʈ���� �� */
    private String ctCtrDesc 	= "";
    
    
    /** ����â���ڵ� */
    private String toWcodeId 	= "";
    /** ����â��� */
    private String toWname 	= "";
    /** ����û ���� */
    private String ptEmgIssReqStatus = "";
    
    /** ������(erp) */
    private String erpYyyy = "";
    /** ������ȣ(erp) */
    private String erpIssNo = "";
    /** ������(erp) */
    private String erpBudat = "";
    
	public String getErpYyyy() {
		return erpYyyy;
	}
	public void setErpYyyy(String erpYyyy) {
		this.erpYyyy = erpYyyy;
	}
	public String getErpIssNo() {
		return erpIssNo;
	}
	public void setErpIssNo(String erpIssNo) {
		this.erpIssNo = erpIssNo;
	}
	public String getErpBudat() {
		return erpBudat;
	}
	public void setErpBudat(String erpBudat) {
		this.erpBudat = erpBudat;
	}
	public String getPtEmgIssReqStatus() {
		return ptEmgIssReqStatus;
	}
	public void setPtEmgIssReqStatus(String ptEmgIssReqStatus) {
		this.ptEmgIssReqStatus = ptEmgIssReqStatus;
	}
	public String getToWcodeId() {
		return toWcodeId;
	}
	public void setToWcodeId(String toWcodeId) {
		this.toWcodeId = toWcodeId;
	}
	public String getToWname() {
		return toWname;
	}
	public void setToWname(String toWname) {
		this.toWname = toWname;
	}
	public String getCtCtrId() {
		return ctCtrId;
	}
	public void setCtCtrId(String ctCtrId) {
		this.ctCtrId = ctCtrId;
	}
	public String getCtCtrDesc() {
		return ctCtrDesc;
	}
	public void setCtCtrDesc(String ctCtrDesc) {
		this.ctCtrDesc = ctCtrDesc;
	}
	public String getPtemgissreqId()
    {
        return ptemgissreqId;
    }
    public void setPtemgissreqId(String ptemgissreqId)
    {
        this.ptemgissreqId = ptemgissreqId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipName()
    {
        return equipName;
    }
    public void setEquipName(String equipName)
    {
        this.equipName = equipName;
    }
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getReqQty()
    {
        return reqQty;
    }
    public void setReqQty(String reqQty)
    {
        this.reqQty = reqQty;
    }
    public String getWoDesc() {
		return woDesc;
	}
	public void setWoDesc(String woDesc) {
		this.woDesc = woDesc;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getPtemgisslistId() {
		return ptemgisslistId;
	}
	public void setPtemgisslistId(String ptemgisslistId) {
		this.ptemgisslistId = ptemgisslistId;
		super.setAuditKey(ptemgisslistId);
	}
	public String getPtemgissStatus() {
		return ptemgissStatus;
	}
	public void setPtemgissStatus(String ptemgissStatus) {
		this.ptemgissStatus = ptemgissStatus;
	}
	public String getPtemgissStatusDesc() {
		return ptemgissStatusDesc;
	}
	public void setPtemgissStatusDesc(String ptemgissStatusDesc) {
		this.ptemgissStatusDesc = ptemgissStatusDesc;
	}
	public String getPtissType() {
		return ptissType;
	}
	public void setPtissType(String ptissType) {
		this.ptissType = ptissType;
	}
	public String getPtissTypeDesc() {
		return ptissTypeDesc;
	}
	public void setPtissTypeDesc(String ptissTypeDesc) {
		this.ptissTypeDesc = ptissTypeDesc;
	}
	public String getPtemgTaskStatus() {
		return ptemgTaskStatus;
	}
	public void setPtemgTaskStatus(String ptemgTaskStatus) {
		this.ptemgTaskStatus = ptemgTaskStatus;
	}
	public String getPtemgTaskStatusDesc() {
		return ptemgTaskStatusDesc;
	}
	public void setPtemgTaskStatusDesc(String ptemgTaskStatusDesc) {
		this.ptemgTaskStatusDesc = ptemgTaskStatusDesc;
	}
	public String getWopartId() {
		return wopartId;
	}
	public void setWopartId(String wopartId) {
		this.wopartId = wopartId;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = CommonUtil.convertDate(issueDate);
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
	public String getIssueBy() {
		return issueBy;
	}
	public void setIssueBy(String issueBy) {
		this.issueBy = issueBy;
	}
	public String getIssueByName() {
		return issueByName;
	}
	public void setIssueByName(String issueByName) {
		this.issueByName = issueByName;
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
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	public String getIssueQty() {
		return issueQty;
	}
	public void setIssueQty(String issueQty) {
		this.issueQty = issueQty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public String getStockQty()
    {
        return stockQty;
    }
    public void setStockQty(String stockQty)
    {
        this.stockQty = stockQty;
    }
	
	
}
