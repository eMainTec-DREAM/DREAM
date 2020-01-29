package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * 비밀번호변경 - 분류  DTO
 * @author  kim21017
 * @version $Id: MaChangePwDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaChangePwDTO extends BaseDTO
{
	/** 사원ID */
	private String userId			= "";
	/** 사원번호 */
	private String userNo			= "";
	/** 사원이름 */
	private String userName			= "";
	/** 기존 비밀번호(확인용) */
	private String oldPwTemp		= "";
	/** 기존 비밀번호(입력용) */
	private String oldPw			= "";
	/** 새 비밀번호 */
	private String newPw			= "";
	/** 새 비밀번호(확인용) */
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