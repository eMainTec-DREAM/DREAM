package dream.consult.rpt.mases.dto;

import common.bean.BaseDTO;

/**
 * 실시간 접속자 공통 DTO
 * @author  kim21017
 * @version $Id: MaSesMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaSesMngCommonDTO extends BaseDTO
{
	/** session ID */
	private String sessionId 	= "";
	/** 사용자 ID */
	private String userNo 		= "";
	/** 사용자명 */
	private String userName		= "";
	/** 접속시간 */
	private String loginTime 	= "";
	/** 핉터-유저번호 */
	private String filterUserNo	= "";
	
	public String getFilterUserNo() {
		return filterUserNo;
	}
	public void setFilterUserNo(String filterUserNo) {
		this.filterUserNo = filterUserNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}
