package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * ��й�ȣ���� - �з�  DTO
 * @author  kim21017
 * @version $Id: MaChangePwDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaChangePwDTO extends BaseDTO
{
	/** ���ID */
	private String userId			= "";
	/** �����ȣ */
	private String userNo			= "";
	/** ����̸� */
	private String userName			= "";
	/** ���� ��й�ȣ(Ȯ�ο�) */
	private String oldPwTemp		= "";
	/** ���� ��й�ȣ(�Է¿�) */
	private String oldPw			= "";
	/** �� ��й�ȣ */
	private String newPw			= "";
	/** �� ��й�ȣ(Ȯ�ο�) */
	private String confirmPw		= "";
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getOldPwTemp() {
		return oldPwTemp;
	}
	public void setOldPwTemp(String oldPwTemp) {
		this.oldPwTemp = oldPwTemp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldPw() {
		return oldPw;
	}
	public void setOldPw(String oldPw) {
		this.oldPw = oldPw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
}