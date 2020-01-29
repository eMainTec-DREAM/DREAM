package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * ������ �����ڵ�  DTO
 * @author   
 * @version $Id:  $
 * @since   1.0
 */
public class MaCdUsrCdDetailDTO extends BaseDTO
{
    /** ȸ���ڵ� - PK */
    private String compNo       = "";
    /** �ڵ� Id - PK */
    private String cdUsrdId     = "";

    /** �ڵ����� Mst Id */
    private String cdUsrmId     = "";
    
    /** �ڵ��ȣ */
    private String cdUsrdNo     = "";
    /** �����ڵ� */
    private String pcdUsrdId    = "";
    /** �����ڵ�� */
    private String pcdUsrdDesc  = "";
    /** �ڵ�� */
    private String description  ="";
    /** �ڵ弳�� */
    private String remark       = "";
    /** ��ȸ���� */
    private String ordNo        = "";
    /** ��뿩�� */
    private String isUse        = "";
    /** ��뿩�θ� */
    private String isUseDesc    = "";
    /** �����ڵ� */
    private String mngCd    = "";
    /** �ڵ����� */
    private String dirType          = "";
    
    
    
    public String getDirType()
    {
        return dirType;
    }
    public void setDirType(String dirType)
    {
        this.dirType = dirType;
    }
    public String getMngCd() {
		return mngCd;
	}
	public void setMngCd(String mngCd) {
		this.mngCd = mngCd;
	}
	public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getCdUsrdId()
    {
        return cdUsrdId;
    }
    public void setCdUsrdId(String cdUsrdId)
    {
        this.cdUsrdId = cdUsrdId;
        super.setAuditKey(cdUsrdId);
    }
    public String getCdUsrmId()
    {
        return cdUsrmId;
    }
    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
    }
    public String getPcdUsrdId()
    {
        return pcdUsrdId;
    }
    public void setPcdUsrdId(String pcdUsrdId)
    {
        this.pcdUsrdId = pcdUsrdId;
    }
    public String getPcdUsrdDesc()
    {
        return pcdUsrdDesc;
    }
    public void setPcdUsrdDesc(String pcdUsrdDesc)
    {
        this.pcdUsrdDesc = pcdUsrdDesc;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getCdUsrdNo()
    {
        return cdUsrdNo;
    }
    public void setCdUsrdNo(String cdUsrdNo)
    {
        this.cdUsrdNo = cdUsrdNo;
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
   
}