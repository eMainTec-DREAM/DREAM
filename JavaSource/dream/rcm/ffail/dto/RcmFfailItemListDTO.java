package dream.rcm.ffail.dto;

import common.bean.BaseDTO;

/**
 * 질의   DTO
 * @author  kim21017
 * @version $Id: RcmFfailItemListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFfailItemListDTO extends BaseDTO
{
	/** 답변ID */
	private String rcmFfailId 	= "";

	public String getRcmFfailId() {
		return rcmFfailId;
	}

	public void setRcmFfailId(String rcmFfailId) {
		this.rcmFfailId = rcmFfailId;
		super.setAuditKey(rcmFfailId);
	}
}