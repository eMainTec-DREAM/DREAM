package dream.tool.list.dto;

import common.bean.BaseDTO;

/**
 * ��������з�(������) ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPttMstrCommonDTO extends BaseDTO
{
    
    /** ȸ���ڵ� */
    private String compNo                   = "";
    /** ����Id */
    private String partId                   = "";
    /** ����ŷ�óId */
    private String ptVendorId               = "";
    /** �ŷ�óId */
    private String vendorId                 = "";
    /** ��뼳�� Id */
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
