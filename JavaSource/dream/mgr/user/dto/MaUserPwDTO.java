package dream.mgr.user.dto;

import common.bean.BaseDTO;

/**
 * 비밀번호변경 - 분류  DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public class MaUserPwDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 사원ID */
	private String userId			= "";
	/** 사원번호 */
	private String userNo			= "";
	/** 사원이름 */
	private String userName			= "";
	/** 비밀번호 */
	private String password         = "";
	/** 비밀번호(확인용) */
	private String confirmPw		= "";
	
	public String getUserNo()
    {
        return userNo;
    }
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getUserId() 
	{
		return userId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getConfirmPw() 
	{
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) 
	{
		this.confirmPw = confirmPw;
	}
}