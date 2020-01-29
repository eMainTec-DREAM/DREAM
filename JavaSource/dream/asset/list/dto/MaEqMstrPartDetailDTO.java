package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� �������� �� dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPartDetailDTO extends BaseDTO
{
	/** �������� id */
	private String eqPartId			= "";
	/** ����ID */
	private String partId			= "";
	/** �����ڵ� */
	private String partNo			= "";
	/** �����, �԰� */
	private String partDesc			= "";
	/** ����� */
	private String partName			= "";
	/** �԰� */
	private String ptSize			= "";
	/** �� */
	private String model			= "";
	/** �������� */
	private String consistQty		= "";
	/** ����id */
	private String eqAsmbId			= "";
	/** ������ */
	private String eqAsmbDesc		= "";
	/** �������� */
	private String useQty			= "";
	/** ���Ƚ�� */
	private String issueTime		= "";
	/** ����������� */
	private String issueFirstDate	= "";
	/** ����������� */
	private String issueLastDate	= "";
	/** �����ȣ */
	private String equipId			= "";
	/** �������۾���ǰid*/
    private String eqCtgPartId      = "";
    /** �۾�����id*/
    private String eqCtgAsmbId      = "";
    /** �۾�������*/
    private String eqCtgAsmbDesc    = "";
    /** ��ȸ���� */
    private String ordNo            = "";
    /** ��뿩�� */
    private String isUse            = "";
    /** (������)����������� */
    private String isEqTypePart     = "";
    /** OLD �������� id */
    private String oldEqPartId		= "";
    /** �������� */
    private String consistNbr		= "";
    /** �����ȣ */
    private String dwgNo		    = "";
    /** ����Section��ȣ */
    private String dwgSectionNo		= "";
    
    public String getConsistNbr()
    {
        return consistNbr;
    }
    public void setConsistNbr(String consistNbr)
    {
        this.consistNbr = consistNbr;
    }
    public String getDwgNo()
    {
        return dwgNo;
    }
    public void setDwgNo(String dwgNo)
    {
        this.dwgNo = dwgNo;
    }
    public String getDwgSectionNo()
    {
        return dwgSectionNo;
    }
    public void setDwgSectionNo(String dwgSectionNo)
    {
        this.dwgSectionNo = dwgSectionNo;
    }
    public String getPartName()
    {
        return partName;
    }
    public void setPartName(String partName)
    {
        this.partName = partName;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
	public String getEqCtgPartId()
    {
        return eqCtgPartId;
    }
    public void setEqCtgPartId(String eqCtgPartId)
    {
        this.eqCtgPartId = eqCtgPartId;
    }
    public String getIsEqTypePart()
    {
        return isEqTypePart;
    }
	public String getOldEqPartId() {
		return oldEqPartId;
	}
	public void setOldEqPartId(String oldEqPartId) {
		this.oldEqPartId = oldEqPartId;
	}
	public void setIsEqTypePart(String isEqTypePart)
    {
        this.isEqTypePart = isEqTypePart;
    }
    public String getEqCtgAsmbId()
    {
        return eqCtgAsmbId;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public void setEqCtgAsmbId(String eqCtgAsmbId)
    {
        this.eqCtgAsmbId = eqCtgAsmbId;
    }
    public String getEqCtgAsmbDesc()
    {
        return eqCtgAsmbDesc;
    }
    public void setEqCtgAsmbDesc(String eqCtgAsmbDesc)
    {
        this.eqCtgAsmbDesc = eqCtgAsmbDesc;
    }
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEqPartId() {
		return eqPartId;
	}
	public void setEqPartId(String eqPartId) {
		this.eqPartId = eqPartId;
		super.setAuditKey(eqPartId);
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
		super.setAuditKey(partId);
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
	public String getConsistQty() {
		return consistQty;
	}
	public void setConsistQty(String consistQty) {
		this.consistQty = CommonUtil.convertMoney(consistQty);
	}
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = CommonUtil.convertMoney(issueTime);
	}
	public String getIssueFirstDate() {
		return issueFirstDate;
	}
	public void setIssueFirstDate(String issueFirstDate) {
		this.issueFirstDate = CommonUtil.convertDate(issueFirstDate);
	}
	public String getIssueLastDate() {
		return issueLastDate;
	}
	public void setIssueLastDate(String issueLastDate) {
		this.issueLastDate = CommonUtil.convertDate(issueLastDate);
	}
}