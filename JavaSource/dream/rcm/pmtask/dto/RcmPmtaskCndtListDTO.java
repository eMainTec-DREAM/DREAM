package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmPmtaskCndtListDTO extends BaseDTO
{
	/**  */
	private String rcmpmtaskcndtId 	= "";

	public String getRcmpmtaskcndtId() {
		return rcmpmtaskcndtId;
	}

	public void setRcmpmtaskcndtId(String rcmpmtaskcndtId) {
		this.rcmpmtaskcndtId = rcmpmtaskcndtId;
		super.setAuditKey(rcmpmtaskcndtId);
	}
}