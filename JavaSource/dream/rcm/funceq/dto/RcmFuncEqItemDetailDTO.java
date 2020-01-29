package dream.rcm.funceq.dto;

import common.bean.BaseDTO;

/**
 * 질의 - 상세  DTO
 * @author  kim21017
 * @version $Id: RcmFuncEqItemDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFuncEqItemDetailDTO extends BaseDTO
{
	/** Key ID */
	private String rcmFfEqId 	= "";

	private String equipId 	= "";
	
	private String equipDesc 	= "";
	
	private String asmbId 	= "";
	
	private String asmbDesc 	= "";
	
	private String isPossible 	= "";
	
	private String remark 	= "";

	private String rcmEqId 	= "";
	
	private String taEquipId 	= "";
	
	
	public String getTaEquipId() {
		return taEquipId;
	}

	public void setTaEquipId(String taEquipId) {
		this.taEquipId = taEquipId;
	}

	public String getRcmEqId() {
		return rcmEqId;
	}

	public void setRcmEqId(String rcmEqId) {
		this.rcmEqId = rcmEqId;
	}

	public String getRcmFfEqId() {
		return rcmFfEqId;
	}

	public void setRcmFfEqId(String rcmFfEqId) {
		this.rcmFfEqId = rcmFfEqId;
		super.setAuditKey(rcmFfEqId);
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}

	public String getAsmbId() {
		return asmbId;
	}

	public void setAsmbId(String asmbId) {
		this.asmbId = asmbId;
	}

	public String getAsmbDesc() {
		return asmbDesc;
	}

	public void setAsmbDesc(String asmbDesc) {
		this.asmbDesc = asmbDesc;
	}

	public String getIsPossible() {
		return isPossible;
	}

	public void setIsPossible(String isPossible) {
		this.isPossible = isPossible;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	
}
