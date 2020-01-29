package dream.rcm.fmea.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFmeaCrityListDTO extends BaseDTO
{
	/**  */
	private String rcmcrityId 	= "";
	
	private String crityvalueId		= "";

	
	public String getCrityvalueId() {
		return crityvalueId;
	}

	public void setCrityvalueId(String crityvalueId) {
		this.crityvalueId = crityvalueId;
	}

	public String getRcmcrityId() {
		return rcmcrityId;
	}

	public void setRcmcrityId(String rcmcrityId) {
		this.rcmcrityId = rcmcrityId;
		super.setAuditKey(rcmcrityId);
	}
	
}