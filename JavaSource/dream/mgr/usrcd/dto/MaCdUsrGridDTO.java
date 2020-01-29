package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * 그리드 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaCdUsrGridDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo       = "";
	/** 코드유형 */
	private String cdUsrmId		= "";
	/** 삭제여부 */
	private String isDelCheck	= "";
	
	public String getCompNo() 
	{
		return compNo;
	}

	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}

	public String getCdUsrmId()
    {
        return cdUsrmId;
    }

    public void setCdUsrmId(String cdUsrmId)
    {
        this.cdUsrmId = cdUsrmId;
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
