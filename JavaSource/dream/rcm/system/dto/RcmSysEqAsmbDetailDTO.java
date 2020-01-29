package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 설비부위 - 상세  DTO
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmSysEqAsmbDetailDTO extends BaseDTO
{
	/** Key ID */
	private String rcmListId 		= "";

	private String rcmEqAsmbId 		= "";
	
	private String rcmEqId 			= "";
	
	private String eqAsmbId 		= "";
	
	private String eqAsmbDesc		= "";
	
	private String remark 			= "";


	public String getRcmListId() {
		return rcmListId;
	}

	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRcmEqAsmbId() {
		return rcmEqAsmbId;
	}

	public void setRcmEqAsmbId(String rcmEqAsmbId) {
		this.rcmEqAsmbId = rcmEqAsmbId;
		super.setAuditKey(rcmEqAsmbId);
	}

	public String getRcmEqId() {
		return rcmEqId;
	}

	public void setRcmEqId(String rcmEqId) {
		this.rcmEqId = rcmEqId;
	}

	public String getEqAsmbId() {
		return eqAsmbId;
	}

	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}

	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}

	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	
}
