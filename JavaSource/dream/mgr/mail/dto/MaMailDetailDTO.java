package dream.mgr.mail.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���ϼ����ڼ��� - �� DTO
 * @author  kim21017
 * @version $Id: MaMailDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaMailDetailDTO extends BaseDTO
{
	/** ���ϸ�ID */
	private String mailListId 			= "";
	/** ���ϸ�No */
	private String mailListNo 			= "";
	/** ���� */
	private String mailDesc 			= "";
	/** ���� */
	private String remark 				= "";
	/** �����ֱ� */
	private String cycle 				= "";
	/** ����Ÿ���ڵ� */
	private String timeType				= "";
	/** ����Ÿ�Ը� */
	private String timeTypeDesc 		= "";
	/** ���۹���ڵ� */
	private String methodType 			= "";
	/** ���۹���� */
	private String methodTypeDesc 		= "";
	/** ��뿩�� */
	private String isActive 			= "";
	/** ���� Script */
	private String titleScript 			= "";
	/** Script */
	private String script 			    = "";
	/** �������� */
	private String startExeDate 		= "";
	/** ����ð� */
	private String startExeTime 		= "";
	/** ������ ����ð� */
	private String lastSchTime  		= "";
	/** ���Ϲ���Ÿ��ID */
	private String mailScopeTypeId  	= "";
	/** ���Ϲ���Ÿ�� �� */
	private String mailScopeTypeDesc  	= "";
	/** ����� Script */
	private String targetScript 		= "";
	/** ���� Script */
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
