package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * �������   DTO
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmSysEqAsmbListDTO extends BaseDTO
{
	/** �������ID */
	private String rcmEqAsmbId 	= "";
	/** ������ID */
	private String rcmEqId 	= "";
	/** ����ID */
	private String equipId 	= "";
	
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
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
}