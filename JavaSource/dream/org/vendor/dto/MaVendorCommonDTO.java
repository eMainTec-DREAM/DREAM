package dream.org.vendor.dto;

import common.bean.BaseDTO;

/**
 * ���þ�ü ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaVendorCommonDTO extends BaseDTO
{
    
    /** ȸ���ڵ� */
    private String compNo                   = "";
    /** �ŷ�óId */
    private String vendorId                 = "";
    
    /** Filter - ȸ���ڵ� */
    private String filterCompNo             = "";
    /** Filter - ��ü�� */
    private String filterVendorDesc         = "";
    /** Filter - ��� */
    private String filterRemark	            = "";
    /** filter - �ŷ�ó���� */
    private String filterVendorType         = "";
    /** filter - �ŷ�ó ������ */
    private String filterVendorTypeDesc     = "";

    
    public String getFilterVendorType()
    {
        return filterVendorType;
    }

    public void setFilterVendorType(String filterVendorType)
    {
        this.filterVendorType = filterVendorType;
    }

    public String getFilterVendorTypeDesc()
    {
        return filterVendorTypeDesc;
    }

    public void setFilterVendorTypeDesc(String filterVendorTypeDesc)
    {
        this.filterVendorTypeDesc = filterVendorTypeDesc;
    }

    public String getFilterCompNo()
    {
        return filterCompNo;
    }

    public String getFilterRemark() {
		return filterRemark;
	}

	public void setFilterRemark(String filterRemark) {
		this.filterRemark = filterRemark;
	}

	public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }

    public String getCompNo()
    {
        return compNo;
    }

    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }

    public String getVendorId()
    {
        return vendorId;
    }

    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
        super.setAuditKey(vendorId);
    }

    public String getFilterVendorDesc()
    {
        return filterVendorDesc;
    }

    public void setFilterVendorDesc(String filterVendorDesc)
    {
        this.filterVendorDesc = filterVendorDesc;
    }
    
}
