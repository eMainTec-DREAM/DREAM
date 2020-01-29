package dream.mgr.mail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 메일수신자설정 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaMailDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaMailDetailDTO extends BaseDTO
{
	/** 메일링ID */
	private String mailListId 			= "";
	/** 메일링No */
	private String mailListNo 			= "";
	/** 제목 */
	private String mailDesc 			= "";
	/** 내용 */
	private String remark 				= "";
	/** 전송주기 */
	private String cycle 				= "";
	/** 전송타입코드 */
	private String timeType				= "";
	/** 전송타입명 */
	private String timeTypeDesc 		= "";
	/** 전송방법코드 */
	private String methodType 			= "";
	/** 전송방법명 */
	private String methodTypeDesc 		= "";
	/** 사용여부 */
	private String isActive 			= "";
	/** 제목 Script */
	private String titleScript 			= "";
	/** Script */
	private String script 			    = "";
	/** 실행일자 */
	private String startExeDate 		= "";
	/** 실행시간 */
	private String startExeTime 		= "";
	/** 마지막 실행시간 */
	private String lastSchTime  		= "";
	/** 메일범위타입ID */
	private String mailScopeTypeId  	= "";
	/** 메일범위타입 명 */
	private String mailScopeTypeDesc  	= "";
	/** 대상자 Script */
	private String targetScript 		= "";
	/** 범위 Script */
	private String scopeScript 			= "";
	
	public String getMailScopeTypeId() {
		return mailScopeTypeId;
	}
	public void setMailScopeTypeId(String mailScopeTypeId) {
		this.mailScopeTypeId = mailScopeTypeId;
	}
	public String getMailScopeTypeDesc() {
		return mailScopeTypeDesc;
	}
	public void setMailScopeTypeDesc(String mailScopeTypeDesc) {
		this.mailScopeTypeDesc = mailScopeTypeDesc;
	}
	public String getTargetScript() {
		return targetScript;
	}
	public void setTargetScript(String targetScript) {
		this.targetScript = targetScript;
	}
	public String getScopeScript() {
		return scopeScript;
	}
	public void setScopeScript(String scopeScript) {
		this.scopeScript = scopeScript;
	}
	public String getLastSchTime() {
		return lastSchTime;
	}
	public void setLastSchTime(String lastSchTime) {
		this.lastSchTime = lastSchTime;
	}
	public String getStartExeDate() {
		return startExeDate;
	}
	public void setStartExeDate(String startExeDate) {
		this.startExeDate = CommonUtil.convertDate(startExeDate);
	}
	public String getStartExeTime() {
		return startExeTime;
	}
	public void setStartExeTime(String startExeTime) {
		this.startExeTime = CommonUtil.convertTime(startExeTime);
	}
	public String getTitleScript() {
		return titleScript;
	}
	public void setTitleScript(String titleScript) {
		this.titleScript = titleScript;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getTimeTypeDesc() {
		return timeTypeDesc;
	}
	public void setTimeTypeDesc(String timeTypeDesc) {
		this.timeTypeDesc = timeTypeDesc;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getMethodTypeDesc() {
		return methodTypeDesc;
	}
	public void setMethodTypeDesc(String methodTypeDesc) {
		this.methodTypeDesc = methodTypeDesc;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getMailListId() {
		return mailListId;
	}
	public void setMailListId(String mailListId) {
		this.mailListId = mailListId;
		super.setAuditKey(mailListId);
	}
	public String getMailListNo() {
		return mailListNo;
	}
	public void setMailListNo(String mailListNo) {
		this.mailListNo = mailListNo;
	}
	public String getMailDesc() {
		return mailDesc;
	}
	public void setMailDesc(String mailDesc) {
		this.mailDesc = mailDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
