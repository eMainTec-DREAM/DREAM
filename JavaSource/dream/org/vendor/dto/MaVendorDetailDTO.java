package dream.org.vendor.dto;

import common.bean.BaseDTO;

/**
 * ���þ�ü - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaVendorDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 	       = "";
	/** �ŷ�óID */
	private String vendorId	       = "";
	/** �ŷ�ó�ڵ� */
	private String vendorNo	       = "";
	/** ��ü�� */
	private String description 	   = "";
	/** �ּ� */
	private String address	       = "";
	/** ��ǥ�ڸ� */
	private String repName         = "";
	/** ��ȭ��ȣ */
	private String office          = "";
	/** ����ڸ� */
	private String person          = "";
	/** �̸����ּ� */
	private String email           = "";
	/** �޴�����ȣ */
	private String mobile          = "";
	/** ��� */
	private String remark          = "";
	/** �������� �ŷ�ó ���� */
	private String isUse          = "";
	
	private String vendorType      = "";
	/** �ŷ�ó������ */
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
