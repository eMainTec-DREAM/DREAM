package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmPmtaskMapListDTO extends BaseDTO
{
	/**  */
	private String rcmpmtaskmapId 	= "";

	public String getRcmpmtaskmapId() {
		return rcmpmtaskmapId;
	}

	public void setRcmpmtaskmapId(String rcmpmtaskmapId) {
		this.rcmpmtaskmapId = rcmpmtaskmapId;
		super.setAuditKey(rcmpmtaskmapId);
	}
}