package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� �������� �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultPartDetailDTO extends BaseDTO
{
	/** �۾���� �������� id */
	private String woPartId			= "";
	/** ��������id*/
	private String partId			= "";
	/** ��������No*/
	private String partNo			= "";
	/** ��������� */
	private String partDesc			= "";
	/** ������ */
	private String useQty 			= "";
	/** ���â��id */
	private String wcodeId 			= "";
	/** ���â��� */
	private String wcodeDesc		= "";
	/** ��� */
	private String remark 			= "";
	/** ��ǰ����ڵ� */
	private String partGrade 		= "";
	/** ��ǰ��޸� */
	private String partGradeDesc 	= "";
	/** ����� */
	private String stockQty			= "";
	/** Serial Part */
	private String isSerialPart		= "";
	
	/** ���� ID */
	private String eqAsmbId		= "";
	/** ���� DESC */
	private String eqAsmbDesc		= "";
	
	/** ���� ID */
	private String equipId		= "";
	/** ���� DESC */
    private String equipDesc      = "";
    /** ��ǰ �԰� */
    private String partSize       = "";
    /** ��ǰ �� */
    private String model		  = "";
    /** MultiSelect Part ID */
    private String multiPartKey     = "";
    /** MultiSelect Part DESC */
    private String multiPartDesc    = "";
    /** MultiSelect Iss ID */
    private String multiIssKey      = "";
    /** MultiSelect Iss DESC */
    private String multiIssDesc     = "";
    /** ������ ID */
    private String ptEmgIssListId   = "";
    /** ��� ID */
    private String ptisslistId      = "";
    /** WO ID */
    private String wkOrId           = "";
    /** ȸ������ */
    private String disUseRtnQty     = "";
    
    
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDisUseRtnQty() {
		return disUseRtnQty;
	}
	public void setDisUseRtnQty(String disUseRtnQty) {
		this.disUseRtnQty = disUseRtnQty;
	}
	public String getWkOrId()
    {
        return wkOrId;
    }
    public void setWkOrId(String wkOrId)
    {
        this.wkOrId = wkOrId;
    }
    public String getMultiIssKey()
    {
        return multiIssKey;
    }
    public void setMultiIssKey(String multiIssKey)
    {
        this.multiIssKey = multiIssKey;
    }
    public String getMultiIssDesc()
    {
        return multiIssDesc;
    }
    public void setMultiIssDesc(String multiIssDesc)
    {
        this.multiIssDesc = multiIssDesc;
    }
    public String getPtisslistId()
    {
        return ptisslistId;
    }
    public void setPtisslistId(String ptisslistId)
    {
        this.ptisslistId = ptisslistId;
    }
    public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getPtEmgIssListId()
    {
        return ptEmgIssListId;
    }
    public void setPtEmgIssListId(String ptEmgIssListId)
    {
        this.ptEmgIssListId = ptEmgIssListId;
    }
    public String getMultiPartKey()
    {
        return multiPartKey;
    }
    public void setMultiPartKey(String multiPartKey)
    {
        this.multiPartKey = multiPartKey;
    }
    public String getMultiPartDesc()
    {
        return multiPartDesc;
    }
    public void setMultiPartDesc(String multiPartDesc)
    {
        this.multiPartDesc = multiPartDesc;
    }
	public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
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
    public String getIsSerialPart() {
		return isSerialPart;
	}
	public void setIsSerialPart(String isSerialPart) {
		this.isSerialPart = isSerialPart;
	}
	public String getStockQty() {
		return stockQty;
	}
	public void setStockQty(String stockQty) {
		this.stockQty = CommonUtil.convertMoney(stockQty);
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
	public String getWoPartId() {
		return woPartId;
	}
	public void setWoPartId(String woPartId) {
		this.woPartId = woPartId;
		super.setAuditKey(woPartId);
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
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWcodeDesc() {
		return wcodeDesc;
	}
	public void setWcodeDesc(String wcodeDesc) {
		this.wcodeDesc = wcodeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}