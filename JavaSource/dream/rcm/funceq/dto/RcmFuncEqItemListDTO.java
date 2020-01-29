package dream.rcm.funceq.dto;

import common.bean.BaseDTO;

/**
 * 질의   DTO
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFuncEqItemListDTO extends BaseDTO
{
	/** 답변ID */
	private String rcmFfEqId 	= "";

	private String multiKey 	= "";
	
	private String multiDesc 	= "";

	public String getMultiKey() {
		return multiKey;
	}

	public void setMultiKey(String multiKey) {
		this.multiKey = multiKey;
	}

	public String getMultiDesc() {
		return multiDesc;
	}

	public void setMultiDesc(String multiDesc) {
		this.multiDesc = multiDesc;
	}

	public String getRcmFfEqId() {
		return rcmFfEqId;
	}

	public void setRcmFfEqId(String rcmFfEqId) {
		this.rcmFfEqId = rcmFfEqId;
		super.setAuditKey(rcmFfEqId);
	}

	

}