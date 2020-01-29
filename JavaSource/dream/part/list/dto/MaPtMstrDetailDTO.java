package dream.part.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��������з�(������) - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaPtMstrDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ��ǰID */
	private String partId 			= "";
	/** �μ���ȣ */
	private String partNo 			= "";
	/** ��ǰ�� */
	private String description 		= "";
	/** ��ǰ�԰� */
	private String ptSize			= "";
	/** ���� */
	private String uom              = "";
	/** ������ */
	private String uomDesc          = "";
	/** ��ǰ��/�԰�/���� - ���հ˻� */
	private String fullDesc         = "";
	/** �� */
	private String model            = "";
	/** ���ۻ� */
	private String maker            = "";
	/** ���ó */
	private String usage            = "";
	/** �ֽű��Ŵܰ� */
	private String lastPrice        = "";
	/** ����,���ڱ��� */
	private String plfType          = "";
	/** ����,���ڱ��� �� */
	private String plfTypeDesc      = "";
	/** ����ǰ/�Ҹ�ǰ ���� */
	private String mroType          = "";
	/** ����ǰ/�Ҹ�ǰ ���� �� */
	private String mroTypeDesc      = "";
	/** ����,���뱸�� */
	private String pcoType          = "";
	/** ����,���뱸�� �� */
	private String pcoTypeDesc      = "";
	/** ����ó */
	private String seller           = "";
	/** ���󸮵�Ÿ��[����] */
	private String leadTime         = "";
	/** ��뿩�� */
	private String isUse 			= "Y";
	/** ��뿩��Desc */
	private String isUseDesc 		= "Y";
	
	/** ���� */
	private String kind            = "";
	/** Variation Class */
	private String varClass        = "";
	/** Variation Class Desc */
	private String varClassDesc        = "";
	/** Part Group  */
	private String partGroup       = "";
	/** part Group Desc */
	private String partGroupDesc   = "";
	/** vendor part code */
	private String vendorPtCode   = "";
	private String partCateg   = "";
	private String partCategDesc   = "";
	private String ptAbcClass   = "";
	private String ptAbcClassDesc   = "";
	
	/** �ֱٱ����԰����� */
	private String lastGrDate          = "";
	/** �ֱ� ������� */
	private String lastIssDate          = "";
	/** ��� */
	private String remark 			= "";
	/** ��ü�ֱ� */
	private String cycle 			= "";
	/** ��ü�ֱ� */
	private String periodType 			= "";
	/** ��ü�ֱ� */
	private String periodTypeDesc 			= "";
	
	private String isSerialPart	= "";
	private String isSerialPartDesc	= "";
	
	private String isStockControl	= "";
	private String isStockControlDesc	= "";
	
	/** ����ǰ���� ID */
	private String isAssetStockId	= "";
	/** ����ǰ���� DESC */
	private String isAssetStockDesc	= "";
	/** ERP ǰ�� */
	private String erpPartNo	= "";
	/** ���� ǰ�� ���� */
	private String isInPart			= "";
	/** ȭ����� ID */
	private String currencyId		= "";
	/** ȭ����� DESC */
	private String currencyDesc		= "";
	
    public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyDesc() {
		return currencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}
    public String getIsInPart() {
		return isInPart;
	}
	public void setIsInPart(String isInPart) {
		this.isInPart = isInPart;
	}
    public String getUomDesc()
    {
        return uomDesc;
    }
    public void setUomDesc(String uomDesc)
    {
        this.uomDesc = uomDesc;
    }
    public String getErpPartNo()
    {
        return erpPartNo;
    }
    public void setErpPartNo(String erpPartNo)
    {
        this.erpPartNo = erpPartNo;
    }
    public String getVarClassDesc()
    {
        return varClassDesc;
    }
    public void setVarClassDesc(String varClassDesc)
    {
        this.varClassDesc = varClassDesc;
    }
    public String getIsAssetStockId() {
		return isAssetStockId;
	}
	public void setIsAssetStockId(String isAssetStockId) {
		this.isAssetStockId = isAssetStockId;
	}
	public String getIsAssetStockDesc() {
		return isAssetStockDesc;
	}
	public void setIsAssetStockDesc(String isAssetStockDesc) {
		this.isAssetStockDesc = isAssetStockDesc;
	}
	public String getIsSerialPartDesc()
    {
        return isSerialPartDesc;
    }
    public void setIsSerialPartDesc(String isSerialPartDesc)
    {
        this.isSerialPartDesc = isSerialPartDesc;
    }
    public String getIsStockControlDesc()
    {
        return isStockControlDesc;
    }
    public void setIsStockControlDesc(String isStockControlDesc)
    {
        this.isStockControlDesc = isStockControlDesc;
    }
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getIsStockControl() {
		return isStockControl;
	}
	public void setIsStockControl(String isStockControl) {
		this.isStockControl = isStockControl;
	}
	public String getIsSerialPart() {
		return isSerialPart;
	}
	public void setIsSerialPart(String isSerialPart) {
		this.isSerialPart = isSerialPart;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getPtAbcClass() {
		return ptAbcClass;
	}
	public void setPtAbcClass(String ptAbcClass) {
		this.ptAbcClass = ptAbcClass;
	}
	public String getPtAbcClassDesc() {
		return ptAbcClassDesc;
	}
	public void setPtAbcClassDesc(String ptAbcClassDesc) {
		this.ptAbcClassDesc = ptAbcClassDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLastGrDate() {
		return lastGrDate;
	}
	public void setLastGrDate(String lastGrDate) {
		this.lastGrDate = CommonUtil.convertDate(lastGrDate) ;
	}
	public String getLastIssDate() {
		return lastIssDate;
	}
	public void setLastIssDate(String lastIssDate) {
		this.lastIssDate = CommonUtil.convertDate(lastIssDate) ;
	}
	public String getKind()
    {
        return kind;
    }
    public void setKind(String kind)
    {
        this.kind = kind;
    }
    public String getVarClass()
    {
        return varClass;
    }
    public void setVarClass(String varClass)
    {
        this.varClass = varClass;
    }
    public String getPartGroup()
    {
        return partGroup;
    }
    public void setPartGroup(String partGroup)
    {
        this.partGroup = partGroup;
    }
    public String getPartGroupDesc()
    {
        return partGroupDesc;
    }
    public void setPartGroupDesc(String partGroupDesc)
    {
        this.partGroupDesc = partGroupDesc;
    }
    public String getMroType() {
		return mroType;
	}
	public void setMroType(String mroType) {
		this.mroType = mroType;
	}
	public String getMroTypeDesc() {
		return mroTypeDesc;
	}
	public void setMroTypeDesc(String mroTypeDesc) {
		this.mroTypeDesc = mroTypeDesc;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
        super.setAuditKey(partId);
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getFullDesc()
    {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc)
    {
        this.fullDesc = fullDesc;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }

    public String getMaker()
    {
        return maker;
    }
    public void setMaker(String maker)
    {
        this.maker = maker;
    }
    public String getUsage()
    {
        return usage;
    }
    public void setUsage(String usage)
    {
        this.usage = usage;
    }
    public String getLastPrice()
    {
        return lastPrice;
    }
    public void setLastPrice(String lastPrice)
    {
        this.lastPrice = CommonUtil.convertMoney(lastPrice);
    }
    public String getPlfType()
    {
        return plfType;
    }
    public void setPlfType(String plfType)
    {
        this.plfType = plfType;
    }
    public String getPlfTypeDesc()
    {
        return plfTypeDesc;
    }
    public void setPlfTypeDesc(String plfTypeDesc)
    {
        this.plfTypeDesc = plfTypeDesc;
    }
    public String getPcoType()
    {
        return pcoType;
    }
    public void setPcoType(String pcoType)
    {
        this.pcoType = pcoType;
    }
    public String getPcoTypeDesc()
    {
        return pcoTypeDesc;
    }
    public void setPcoTypeDesc(String pcoTypeDesc)
    {
        this.pcoTypeDesc = pcoTypeDesc;
    }
    public String getSeller()
    {
        return seller;
    }
    public void setSeller(String seller)
    {
        this.seller = seller;
    }
    public String getLeadTime()
    {
        return leadTime;
    }
    public void setLeadTime(String leadTime)
    {
        this.leadTime = leadTime;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
	public String getVendorPtCode() {
		return vendorPtCode;
	}
	public void setVendorPtCode(String vendorPtCode) {
		this.vendorPtCode = vendorPtCode;
	}
	public String getPartCateg() {
		return partCateg;
	}
	public void setPartCateg(String partCateg) {
		this.partCateg = partCateg;
	}
	public String getPartCategDesc() {
		return partCategDesc;
	}
	public void setPartCategDesc(String partCategDesc) {
		this.partCategDesc = partCategDesc;
	}
    
    
	
}
