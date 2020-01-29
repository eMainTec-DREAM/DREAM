package dream.consult.program.help.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaHelpCommonDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                  		= "";
	/** Key Id */
	private String helpdeskId                   = "";
	/**  */
	private String description					= "";
	/**  */
	private String reqDateFrom					= "";
	/**  */
	private String reqDateTo					= "";
	/**  */
	private String helpdeskStatus				= "";
	/**  */
	private String helpdeskStatusDesc			= "";
	/**  */
	private String request						= "";
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReqDateFrom() {
		return reqDateFrom;
	}
	public void setReqDateFrom(String reqDateFrom) {
		this.reqDateFrom = reqDateFrom;
	}
	public String getReqDateTo() {
		return reqDateTo;
	}
	public void setReqDateTo(String reqDateTo) {
		this.reqDateTo = reqDateTo;
	}
	public String getHelpdeskStatus() {
		return helpdeskStatus;
	}
	public void setHelpdeskStatus(String helpdeskStatus) {
		this.helpdeskStatus = helpdeskStatus;
	}
	public String getHelpdeskStatusDesc() {
		return helpdeskStatusDesc;
	}
	public void setHelpdeskStatusDesc(String helpdeskStatusDesc) {
		this.helpdeskStatusDesc = helpdeskStatusDesc;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getHelpdeskId() {
		return helpdeskId;
	}
	public void setHelpdeskId(String helpdeskId) {
		this.helpdeskId = helpdeskId;
		super.setAuditKey(helpdeskId);
	}
	
	
}
