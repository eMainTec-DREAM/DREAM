package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * ������ڵ���� Grid DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaCdUsrDetailGridDTO1 extends BaseDTO
{
    //===================================
	/** ȸ���ڵ� */
	private String compNo               = "";
    /** �ڵ� ���� */
    private String dirType              = "";
    /** �ڵ� */
    private String code                 = "";
    /** ���� �ڵ� */
    private String parentCode           = "";
    /** �����ڵ�� */
    private String parentCodeName       = "";
    /** �ڵ�� */
    private String description          = "";
    /** ���� */
    private String ordNo                = "";
    /** �󼼼��� */
    private String remark               = "";
    /** ��뿩�� */
    private String isUse                = "";
	/** �������� */
	private String isDelCheck	= "";
      
    public String getDirType()
    {
        return dirType;
    }
    public void setDirType(String dirType)
    {
        this.dirType = dirType;
    }
    public String getParentCode()
    {
        return parentCode;
    }
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }
    public String getParentCodeName()
    {
        return parentCodeName;
    }
    public void setParentCodeName(String parentCodeName)
    {
        this.parentCodeName = parentCodeName;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getOrdNo() 
	{
		return ordNo;
	}
	public void setOrdNo(String ordNo) 
	{
		this.ordNo = ordNo;
	}
	public String getIsUse() 
	{
		return isUse;
	}
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}
	public String getIsDelCheck() {
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) {
		this.isDelCheck = isDelCheck;
	}
    
}
