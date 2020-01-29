package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 보전자재분류(마스터) 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtMstrCommonDTO extends BaseDTO
{
    
    /** 회사코드 */
    private String compNo                   = "";
    /** 자재Id */
    private String partId                   = "";
    /** 자재거래처Id */
    private String ptVendorId               = "";
    /** 거래처Id */
    private String vendorId                 = "";
    /** 설비부품관계 Id */
    private String eqPartId                 = "";
    /** 사용부서 Id */
    private String ptUsedDeptId             = "";
    
    /** Filter-회사코드 */
    private String filterCompNo             = "";
    /** 제품명 규격/단위 */
    private String filterPartNameSize       = "";
    /** 부품번호 */
    private String filterPartNo             = "";
    /** 제작사 */
    private String filterMaker              = "";
    /** 구입처 */
    private String filterSeller             = "";
    /** 모델 */
    private String filterModel              = "";
    /** 사용처 */
    private String filterUsage              = "";
    /** 내/외자코드 */
    private String filterPlfType            = "";
    /** 내/외자명 */
    private String filterPlfTypeDesc        = "";
    /** 수리/소모 구분코드 */
    private String filterMroType            = "";
    /** 수리/소모 구분명 */
    private String filterMroTypeDesc        = "";
    /** 사용여부코드 */
    private String filterIsUse              = "";
    /** 사용여부명 */
    private String filterIsUseDesc          = "";
    /** 부서Id */
    private String filterDeptId             = "";
    /** 부서명 */
    private String filterDeptDesc          = "";
    /** 내부품번여부 */
    private String filterIsInPart             = "";
    /** 내부품번여부 */
    private String filterIsInPartDesc          = "";
    /** 업체품번 */
    private String filterVendorPtCode          = "";
    /** 자재그룹 */
    private String filterPartGroup              = "";
    /** 자재그룹명 */
    private String filterPartGroupDesc          = "";
    /** 필터 수신 시작 요청일자 */
	private String filterOutStartUpdDate		= "";
	/** 필터 수신 끝 일자 */
	private String filterOutEndUpdDate			= "";
	/** 필터 자재중요도 명 */
	private String filterPtAbcClassDesc			= "";
	/** 필터 Old # */
	private String filterOldPartNo				= "";
	/** 필터 자재중요도 */
	private String filterPtAbcClass				= "";
	/** 필터 사용설비 Id */
    private String filterEquipId                 = "";
    /** 필터 사용설비 명 */
    private String filterEquipDesc              = "";
    /** 필터 저장품여부 Id */
    private String filterIsAssetStockId         = "";
    /** 필터 저장품여부 명 */
    private String filterIsAssetStockDesc       = "";
    /** 필터 비고 */
    private String filterRemark              = "";
    /**  */
	private String sapParts						= "";
	
	private String isSerialPart					= "";
	
	private String isStockControl				= "";
	/** 필터 ERP 품번 */
	private String filterErpPartNo				= "";
	
	private String wcodeId						= "";
	/** 엑셀업로드시 set할 exceltab_no  */
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
