package dream.mgr.mail.dto;

import common.bean.BaseDTO;

/**
 * ���ϼ����ڼ��� - ������  DTO
 * @author  kim21017
 * @version $Id: MaMailUsrListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaMailUsrListDTO extends BaseDTO
{
	/** ������ID */
	private String mailUsrId 	= "";

	public String getMailUsrId() {
		return mailUsrId;
	}

	public void setMailUsrId(String mailUsrId) {
		this.mailUsrId = mailUsrId;
		super.setAuditKey(mailUsrId);
	}
}