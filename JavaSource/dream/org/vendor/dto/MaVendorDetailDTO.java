package dream.org.vendor.dto;

import common.bean.BaseDTO;

/**
 * 관련업체 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaVendorDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 	       = "";
	/** 거래처ID */
	private String vendorId	       = "";
	/** 거래처코드 */
	private String vendorNo	       = "";
	/** 업체명 */
	private String description 	   = "";
	/** 주소 */
	private String address	       = "";
	/** 대표자명 */
	private String repName         = "";
	/** 전화번호 */
	private String office          = "";
	/** 담당자명 */
	private String person          = "";
	/** 이메일주소 */
	private String email           = "";
	/** 휴대폰번호 */
	private String mobile          = "";
	/** 비고 */
	private String remark          = "";
	/** 설비자재 거래처 여부 */
	private String isUse          = "";
	
	private String vendorType      = "";
	/** 거래처종류명 */
	private String vendorTypeDesc  = "";
	
	
    public String getVendorType()
    {
        return vendorType;
    }
    public void setVendorType(String vendorType)
    {
        this.vendorType = vendorType;
    }
    public String getVendorTypeDesc()
    {
        return vendorTypeDesc;
    }
    public void setVendorTypeDesc(String vendorTypeDesc)
    {
        this.vendorTypeDesc = vendorTypeDesc;
    }
    public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
    public String getVendorNo()
    {
        return vendorNo;
    }
    public void setVendorNo(String vendorNo)
    {
        this.vendorNo = vendorNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getRepName()
    {
        return repName;
    }
    public void setRepName(String repName)
    {
        this.repName = repName;
    }
    public String getOffice()
    {
        return office;
    }
    public void setOffice(String office)
    {
        this.office = office;
    }
    public String getPerson()
    {
        return person;
    }
    public void setPerson(String person)
    {
        this.person = person;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
