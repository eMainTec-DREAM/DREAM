package dream.mgr.user.dto;

import common.bean.BaseDTO;

/**
 * ��й�ȣ���� - �з�  DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public class MaUserPwDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ���ID */
	private String userId			= "";
	/** �����ȣ */
	private String userNo			= "";
	/** ����̸� */
	private String userName			= "";
	/** ��й�ȣ */
	private String password         = "";
	/** ��й�ȣ(Ȯ�ο�) */
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