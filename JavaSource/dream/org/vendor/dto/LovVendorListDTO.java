package dream.org.vendor.dto;

import common.bean.BaseDTO;

/**
 * 거래처검색 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovVendorListDTO extends BaseDTO
{
    /** Search Code */
    private String vendorNo     = "";
    /** Search Description */
    private String vendorDesc   = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String codeType 	= "";
    /** 설비자재거래처여부*/
    private String isUse	 	= "";
    
    public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
    
    public String getVendorNo()
    {
        return vendorNo;
    }
    public void setVendorNo(String vendorNo)
    {
        this.vendorNo = vendorNo;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getExtCode1()
    {
        return extCode1;
    }
    public void setExtCode1(String extCode1)
    {
        this.extCode1 = extCode1;
    }
    public String getExtCode2()
    {
        return extCode2;
    }
    public void setExtCode2(String extCode2)
    {
        this.extCode2 = extCode2;
    }
    public String getCodeType()
    {
        return codeType;
    }
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }
    

}
