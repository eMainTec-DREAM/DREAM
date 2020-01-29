package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품거래처 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrVendorDetailDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo           = "";
    /** 자재거래처Id - PK */
    private String ptVendorId       = "";  
    /** 자재Id */
    private String partId           = "";  
    /** 거래처Id */
    private String vendorId         = "";
    /** 거래처No */
    private String vendorNo         = "";
    /** 거래처 */
    private String vendorDesc       = "";
    /** 주소/대표 */
    private String addrNrepName     = "";
    /** 담당자 */
    private String person           = "";
    /** 전화 */
    private String office           = "";
    /** 핸드폰 */
    private String mobile           = "";
    /** 이메일 */
    private String email            = "";
    
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getPtVendorId()
    {
        return ptVendorId;
    }
    public void setPtVendorId(String ptVendorId)
    {
        this.ptVendorId = ptVendorId;
    }
    public String getVendorNo()
    {
        return vendorNo;
    }
    public void setVendorNo(String vendorNo)
    {
        this.vendorNo = vendorNo;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getAddrNrepName()
    {
        return addrNrepName;
    }
    public void setAddrNrepName(String addrNrepName)
    {
        this.addrNrepName = addrNrepName;
    }
    public String getPerson()
    {
        return person;
    }
    public void setPerson(String person)
    {
        this.person = person;
    }
    public String getOffice()
    {
        return office;
    }
    public void setOffice(String office)
    {
        this.office = office;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }  
		
}