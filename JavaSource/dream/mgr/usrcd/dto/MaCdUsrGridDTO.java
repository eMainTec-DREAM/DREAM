package dream.mgr.usrcd.dto;

import common.bean.BaseDTO;

/**
 * �׸��� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaCdUsrGridDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo       = "";
	/** �ڵ����� */
	private String cdUsrmId		= "";
	/** �������� */
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
