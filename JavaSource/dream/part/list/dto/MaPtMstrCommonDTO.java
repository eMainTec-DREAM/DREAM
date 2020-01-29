package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��������з�(������) ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtMstrCommonDTO extends BaseDTO
{
    
    /** ȸ���ڵ� */
    private String compNo                   = "";
    /** ����Id */
    private String partId                   = "";
    /** ����ŷ�óId */
    private String ptVendorId               = "";
    /** �ŷ�óId */
    private String vendorId                 = "";
    /** �����ǰ���� Id */
    private String eqPartId                 = "";
    /** ���μ� Id */
    private String ptUsedDeptId             = "";
    
    /** Filter-ȸ���ڵ� */
    private String filterCompNo             = "";
    /** ��ǰ�� �԰�/���� */
    private String filterPartNameSize       = "";
    /** ��ǰ��ȣ */
    private String filterPartNo             = "";
    /** ���ۻ� */
    private String filterMaker              = "";
    /** ����ó */
    private String filterSeller             = "";
    /** �� */
    private String filterModel              = "";
    /** ���ó */
    private String filterUsage              = "";
    /** ��/�����ڵ� */
    private String filterPlfType            = "";
    /** ��/���ڸ� */
    private String filterPlfTypeDesc        = "";
    /** ����/�Ҹ� �����ڵ� */
    private String filterMroType            = "";
    /** ����/�Ҹ� ���и� */
    private String filterMroTypeDesc        = "";
    /** ��뿩���ڵ� */
    private String filterIsUse              = "";
    /** ��뿩�θ� */
    private String filterIsUseDesc          = "";
    /** �μ�Id */
    private String filterDeptId             = "";
    /** �μ��� */
    private String filterDeptDesc          = "";
    /** ����ǰ������ */
    private String filterIsInPart             = "";
    /** ����ǰ������ */
    private String filterIsInPartDesc          = "";
    /** ��üǰ�� */
    private String filterVendorPtCode          = "";
    /** ����׷� */
    private String filterPartGroup              = "";
    /** ����׷�� */
    private String filterPartGroupDesc          = "";
    /** ���� ���� ���� ��û���� */
	private String filterOutStartUpdDate		= "";
	/** ���� ���� �� ���� */
	private String filterOutEndUpdDate			= "";
	/** ���� �����߿䵵 �� */
	private String filterPtAbcClassDesc			= "";
	/** ���� Old # */
	private String filterOldPartNo				= "";
	/** ���� �����߿䵵 */
	private String filterPtAbcClass				= "";
	/** ���� ��뼳�� Id */
    private String filterEquipId                 = "";
    /** ���� ��뼳�� �� */
    private String filterEquipDesc              = "";
    /** ���� ����ǰ���� Id */
    private String filterIsAssetStockId         = "";
    /** ���� ����ǰ���� �� */
    private String filterIsAssetStockDesc       = "";
    /** ���� ��� */
    private String filterRemark              = "";
    /**  */
	private String sapParts						= "";
	
	private String isSerialPart					= "";
	
	private String isStockControl				= "";
	/** ���� ERP ǰ�� */
	private String filterErpPartNo				= "";
	
	private String wcodeId						= "";
	/** �������ε�� set�� exceltab_no  */
	private String exceltabNo					= "";
	
    public String getFilterSeller()
    {
        return filterSeller;
    }
    public void setFilterSeller(String filterSeller)
    {
        this.filterSeller = filterSeller;
    }
    public String getExceltabNo() {
		return exceltabNo;
	}
	public void setExceltabNo(String exceltabNo) {
		this.exceltabNo = exceltabNo;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
    public String getFilterErpPartNo()
    {
        return filterErpPartNo;
    }
    public void setFilterErpPartNo(String filterErpPartNo)
    {
        this.filterErpPartNo = filterErpPartNo;
    }
    public String getFilterIsAssetStockId() {
		return filterIsAssetStockId;
	}
	public void setFilterIsAssetStockId(String filterIsAssetStockId) {
		this.filterIsAssetStockId = filterIsAssetStockId;
	}
	public String getFilterIsAssetStockDesc() {
		return filterIsAssetStockDesc;
	}
	public void setFilterIsAssetStockDesc(String filterIsAssetStockDesc) {
		this.filterIsAssetStockDesc = filterIsAssetStockDesc;
	}
	public String getFilterRemark()
    {
        return filterRemark;
    }
    public void setFilterRemark(String filterRemark)
    {
        this.filterRemark = filterRemark;
    }
    public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
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
	public String getFilterOldPartNo() {
		return filterOldPartNo;
	}
	public void setFilterOldPartNo(String filterOldPartNo) {
		this.filterOldPartNo = filterOldPartNo;
	}
	public String getFilterPtAbcClassDesc() {
		return filterPtAbcClassDesc;
	}
	public void setFilterPtAbcClassDesc(String filterPtAbcClassDesc) {
		this.filterPtAbcClassDesc = filterPtAbcClassDesc;
	}
	public String getFilterPtAbcClass() {
		return filterPtAbcClass;
	}
	public void setFilterPtAbcClass(String filterPtAbcClass) {
		this.filterPtAbcClass = filterPtAbcClass;
	}
	public String getSapParts() {
		return sapParts;
	}
	public void setSapParts(String sapParts) {
		this.sapParts = sapParts;
	}
	public String getFilterPartGroup() {
		return filterPartGroup;
	}
	public void setFilterPartGroup(String filterPartGroup) {
		this.filterPartGroup = filterPartGroup;
	}
	public String getFilterPartGroupDesc() {
		return filterPartGroupDesc;
	}
	public void setFilterPartGroupDesc(String filterPartGroupDesc) {
		this.filterPartGroupDesc = filterPartGroupDesc;
	}
	public String getFilterMroType() {
		return filterMroType;
	}
	public void setFilterMroType(String filterMroType) {
		this.filterMroType = filterMroType;
	}
	public String getFilterMroTypeDesc() {
		return filterMroTypeDesc;
	}
	public void setFilterMroTypeDesc(String filterMroTypeDesc) {
		this.filterMroTypeDesc = filterMroTypeDesc;
	}
	public String getFilterPartNo()
    {
        return filterPartNo;
    }
    public void setFilterPartNo(String filterPartNo)
    {
        this.filterPartNo = filterPartNo;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getEqPartId()
    {
        return eqPartId;
    }
    public void setEqPartId(String eqPartId)
    {
        this.eqPartId = eqPartId;
        super.setAuditKey(eqPartId);
    }
    public String getPtUsedDeptId()
    {
        return ptUsedDeptId;
    }
    public void setPtUsedDeptId(String ptUsedDeptId)
    {
        this.ptUsedDeptId = ptUsedDeptId;
    }
    public String getFilterPlfType()
    {
        return filterPlfType;
    }
    public void setFilterPlfType(String filterPlfType)
    {
        this.filterPlfType = filterPlfType;
    }
    public String getFilterPlfTypeDesc()
    {
        return filterPlfTypeDesc;
    }
    public void setFilterPlfTypeDesc(String filterPlfTypeDesc)
    {
        this.filterPlfTypeDesc = filterPlfTypeDesc;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
    public String getFilterIsUseDesc()
    {
        return filterIsUseDesc;
    }
    public void setFilterIsUseDesc(String filterIsUseDesc)
    {
        this.filterIsUseDesc = filterIsUseDesc;
    }
    public String getPtVendorId()
    {
        return ptVendorId;
    }
    public void setPtVendorId(String ptVendorId)
    {
        this.ptVendorId = ptVendorId;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
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
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
    public String getFilterMaker()
    {
        return filterMaker;
    }
    public void setFilterMaker(String filterMaker)
    {
        this.filterMaker = filterMaker;
    }
    public String getFilterModel()
    {
        return filterModel;
    }
    public void setFilterModel(String filterModel)
    {
        this.filterModel = filterModel;
    }
    public String getFilterUsage()
    {
        return filterUsage;
    }
    public void setFilterUsage(String filterUsage)
    {
        this.filterUsage = filterUsage;
    }
	public String getFilterIsInPart() {
		return filterIsInPart;
	}
	public void setFilterIsInPart(String filterIsInPart) {
		this.filterIsInPart = filterIsInPart;
	}
	public String getFilterIsInPartDesc() {
		return filterIsInPartDesc;
	}
	public void setFilterIsInPartDesc(String filterIsInPartDesc) {
		this.filterIsInPartDesc = filterIsInPartDesc;
	}
	public String getFilterVendorPtCode() {
		return filterVendorPtCode;
	}
	public void setFilterVendorPtCode(String filterVendorPtCode) {
		this.filterVendorPtCode = filterVendorPtCode;
	}
	public String getFilterOutStartUpdDate() {
		return filterOutStartUpdDate;
	}
	public void setFilterOutStartUpdDate(String filterOutStartUpdDate) {
		this.filterOutStartUpdDate = filterOutStartUpdDate;
	}
	public String getFilterOutEndUpdDate() {
		return filterOutEndUpdDate;
	}
	public void setFilterOutEndUpdDate(String filterOutEndUpdDate) {
		this.filterOutEndUpdDate = filterOutEndUpdDate;
	}
    
    
    
}
