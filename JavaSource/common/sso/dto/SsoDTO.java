package common.sso.dto;

import common.bean.BaseDTO;

/**
 * SSO DTO
 * @author js.lee
 * @version $Id: Exp $
 * @since 1.0
 *
 */
public class SsoDTO extends BaseDTO
{
	/** »ç¿ëÀÚ ID */
	private String userNo = "";
	
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
    
}
