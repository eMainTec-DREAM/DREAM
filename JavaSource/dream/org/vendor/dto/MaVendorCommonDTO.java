package dream.org.vendor.dto;

import common.bean.BaseDTO;

/**
 * 관련업체 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaVendorCommonDTO extends BaseDTO
{
    
    /** 회사코드 */
    private String compNo                   = "";
    /** 거래처Id */
    private String vendorId                 = "";
    
    /** Filter - 회사코드 */
    private String filterCompNo             = "";
    /** Filter - 업체명 */
    private String filterVendorDesc         = "";
    /** Filter - 비고 */
    private String filterRemark	            = "";
    /** filter - 거래처종류 */
    private String filterVendorType         = "";
    /** filter - 거래처 종류명 */
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
