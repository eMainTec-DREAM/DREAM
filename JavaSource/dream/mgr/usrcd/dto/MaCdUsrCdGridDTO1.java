package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * �� List �׸��� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaCdUsrCdGridDTO1 extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo       = "";
	/** �ڵ����� Id */
	private String cdUsrmId		= "";
	/** ���ڵ� Id */
	private String cdUsrdId     = "";
	/** �������� */
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
