package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ�ŷ�ó �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtMstrVendorDetailDTO extends BaseDTO
{
    /** ȸ���ڵ� - PK */
    private String compNo           = "";
    /** ����ŷ�óId - PK */
    private String ptVendorId       = "";  
    /** ����Id */
    private String partId           = "";  
    /** �ŷ�óId */
    private String vendorId         = "";
    /** �ŷ�óNo */
    private String vendorNo         = "";
    /** �ŷ�ó */
    private String vendorDesc       = "";
    /** �ּ�/��ǥ */
    private String addrNrepName     = "";
    /** ����� */
    private String person           = "";
    /** ��ȭ */
    private String office           = "";
    /** �ڵ��� */
    private String mobile           = "";
    /** �̸��� */
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