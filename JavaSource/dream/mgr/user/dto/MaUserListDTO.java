package dream.mgr.user.dto;

import common.bean.BaseDTO;

/**
 * ����� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaUserListDTO extends BaseDTO
{
	/** ����� ID */
	private String userId 			= "";
	
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

}
