package dream.mgr.mail.dto;

import common.bean.BaseDTO;

/**
 * 메일수신자설정 공통 DTO
 * @author  kim21017
 * @version $Id: MaMailCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaMailCommonDTO extends BaseDTO
{
	/** 메일링ID */
	private String mailListId 				= "";
	/** 필터 제목 */
	private String filterMailDesc			= "";
	/** 선택된 상세페이지ID ( 범위타입에 의해 detail 페이지가 달라진다.) */
	private String mailDetailPageId			= "";
	/** 범위타입 ID */
	private String mailScopeTypeId			= "";
	
	
	public String getMailDetailPageId() {
		return mailDetailPageId;
	}
	public void setMailDetailPageId(String mailDetailPageId) {
		this.mailDetailPageId = mailDetailPageId;
	}
	public String getMailScopeTypeId() {
		return mailScopeTypeId;
	}
	public void setMailScopeTypeId(String mailScopeTypeId) {
		this.mailScopeTypeId = mailScopeTypeId;
	}
	public String getMailListId() {
		return mailListId;
	}
	public void setMailListId(String mailListId) {
		this.mailListId = mailListId;
		super.setAuditKey(mailListId);
	}
	public String getFilterMailDesc() {
		return filterMailDesc;
	}
	public void setFilterMailDesc(String filterMailDesc) {
		this.filterMailDesc = filterMailDesc;
	}
}
