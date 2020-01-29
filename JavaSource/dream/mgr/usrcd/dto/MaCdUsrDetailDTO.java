package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * ������ڵ���� DTO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public class MaCdUsrDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� - PK */
    private String compNo           = "";
    /** �ڵ����� Id  - PK */
    private String cdUsrmId         = "";
    /** �ڵ����� No */
    private String cdUsrmNo         = "";
    /** �ڵ����� */
    private String dirType          = "";
    /** �ڵ�� */
    private String description      = "";
    /** �ڵ弳�� */
    private String remark           = "";
    /** ��뿩�� */
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