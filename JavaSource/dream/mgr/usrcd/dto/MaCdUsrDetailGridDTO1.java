package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * 사용자코드관리 Grid DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaCdUsrDetailGridDTO1 extends BaseDTO
{
    //===================================
	/** 회사코드 */
	private String compNo               = "";
    /** 코드 종류 */
    private String dirType              = "";
    /** 코드 */
    private String code                 = "";
    /** 상위 코드 */
    private String parentCode           = "";
    /** 상위코드명 */
    private String parentCodeName       = "";
    /** 코드명 */
    private String description          = "";
    /** 순번 */
    private String ordNo                = "";
    /** 상세설명 */
    private String remark               = "";
    /** 사용여부 */
    private String isUse                = "";
	/** 삭제여부 */
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
