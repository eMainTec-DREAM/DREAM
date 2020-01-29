package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * 유형별 세부코드  DTO
 * @author   
 * @version $Id:  $
 * @since   1.0
 */
public class MaCdUsrCdDetailDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo       = "";
    /** 코드 Id - PK */
    private String cdUsrdId     = "";

    /** 코드종류 Mst Id */
    private String cdUsrmId     = "";
    
    /** 코드번호 */
    private String cdUsrdNo     = "";
    /** 상위코드 */
    private String pcdUsrdId    = "";
    /** 상위코드명 */
    private String pcdUsrdDesc  = "";
    /** 코드명 */
    private String description  ="";
    /** 코드설명 */
    private String remark       = "";
    /** 조회순서 */
    private String ordNo        = "";
    /** 사용여부 */
    private String isUse        = "";
    /** 사용여부명 */
    private String isUseDesc    = "";
    /** 관리코드 */
    private String mngCd    = "";
    /** 코드종류 */
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