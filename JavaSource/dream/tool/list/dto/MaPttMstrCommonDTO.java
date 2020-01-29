package dream.tool.list.dto;

import common.bean.BaseDTO;

/**
 * 보전자재분류(마스터) 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPttMstrCommonDTO extends BaseDTO
{
    
    /** 회사코드 */
    private String compNo                   = "";
    /** 자재Id */
    private String partId                   = "";
    /** 자재거래처Id */
    private String ptVendorId               = "";
    /** 거래처Id */
    private String vendorId                 = "";
    /** 사용설비 Id */
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
    
    
    
}
