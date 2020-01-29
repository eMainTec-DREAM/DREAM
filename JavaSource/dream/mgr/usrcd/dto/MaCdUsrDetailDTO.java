package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * 사용자코드관리 DTO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public class MaCdUsrDetailDTO extends BaseDTO
{
	/** 회사코드 - PK */
    private String compNo           = "";
    /** 코드종류 Id  - PK */
    private String cdUsrmId         = "";
    /** 코드종류 No */
    private String cdUsrmNo         = "";
    /** 코드종류 */
    private String dirType          = "";
    /** 코드명 */
    private String description      = "";
    /** 코드설명 */
    private String remark           = "";
    /** 사용여부 */
    private String isUse            = "";
    private String isUseDesc        = "";
    
	public String getCdUsrmNo()
    {
        return cdUsrmNo;
    }
    public void setCdUsrmNo(String cdUsrmNo)
    {
        this.cdUsrmNo = cdUsrmNo;
    }
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getCdUsrmId()
    {
        return cdUsrmId;
    }
    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
        super.setAuditKey(cdUsrmId);
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getDirType() 
	{
		return dirType;
	}
	public void setDirType(String dirType) 
	{
		this.dirType = dirType;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getRemark() 
	{
		return remark;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	public String getIsUse() 
	{
		return isUse;
	}
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}

}