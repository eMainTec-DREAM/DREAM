package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * 상세 List 그리드 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaCdUsrCdGridDTO1 extends BaseDTO
{
	/** 회사코드 */
	private String compNo       = "";
	/** 코드종류 Id */
	private String cdUsrmId		= "";
	/** 상세코드 Id */
	private String cdUsrdId     = "";
	/** 삭제여부 */
	private String isDelCheck	= "";
	
	public String getCdUsrmId()
    {
        return cdUsrmId;
    }

    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
    }

    public String getCdUsrdId()
    {
        return cdUsrdId;
    }

    public void setCdUsrdId(String cdUsrdId)
    {
        this.cdUsrdId = cdUsrdId;
    }

    public String getCompNo() 
	{
		return compNo;
	}

	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}

	public String getIsDelCheck() 
	{
		return isDelCheck;
	}

	public void setIsDelCheck(String isDelCheck) 
	{
		this.isDelCheck = isDelCheck;
	}
}
