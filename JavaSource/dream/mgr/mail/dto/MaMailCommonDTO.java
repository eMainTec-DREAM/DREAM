package dream.mgr.mail.dto;

import common.bean.BaseDTO;

/**
 * ���ϼ����ڼ��� ���� DTO
 * @author  kim21017
 * @version $Id: MaMailCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaMailCommonDTO extends BaseDTO
{
	/** ���ϸ�ID */
	private String mailListId 				= "";
	/** ���� ���� */
	private String filterMailDesc			= "";
	/** ���õ� ��������ID ( ����Ÿ�Կ� ���� detail �������� �޶�����.) */
	private String mailDetailPageId			= "";
	/** ����Ÿ�� ID */
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
