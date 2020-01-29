package dream.consult.consult.user.dto;

import common.bean.BaseDTO;

/**
 * 사용자 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class McUserListDTO extends BaseDTO
{
	/** 사용자 ID */
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
