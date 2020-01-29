package dream.mgr.mail.dto;

import common.bean.BaseDTO;

/**
 * 메일수신자설정 - 수신자  DTO
 * @author  kim21017
 * @version $Id: MaMailUsrListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaMailUsrListDTO extends BaseDTO
{
	/** 수신자ID */
	private String mailUsrId 	= "";

	public String getMailUsrId() {
		return mailUsrId;
	}

	public void setMailUsrId(String mailUsrId) {
		this.mailUsrId = mailUsrId;
		super.setAuditKey(mailUsrId);
	}
}