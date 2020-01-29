package dream.rcm.system.dto;

import common.bean.BaseDTO;

/**
 * 설비설정 상세 DTO
 * @author  jung7126
 * @version $Id: RcmSysEqDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class RcmSysEqDetailDTO extends BaseDTO
{
	/** RCM분석ID */
	private String rcmListId		= "";
	/** RCM설비id */
	private String rcmEqId			= "";
	/** 설비ID */
	private String equipId			= "";
	/** 설비No */
	private String itemNo			= "";
	/** 설비종류ID */
	private String eqCtgId			= "";
	/** 설비위치ID */
	private String eqLocId			= "";
	/** 설비상태ID */
	private String eqStatusId		= "";
	/** 설비명 */
	private String description		= "";
	/** 설비상태명 */
	private String eqStatusDesc		= "";
	/** 설비종류명 */
	private String eqCtgDesc		= "";
	/** 설비위치명 */
	private String eqLocDesc		= "";
	/** 비고 */
	private String remark			= "";
	
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
	}
	public String getRcmEqId() {
		return rcmEqId;
	}
	public void setRcmEqId(String rcmEqId) {
		this.rcmEqId = rcmEqId;
		super.setAuditKey(rcmEqId);
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqStatusId() {
		return eqStatusId;
	}
	public void setEqStatusId(String eqStatusId) {
		this.eqStatusId = eqStatusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEqStatusDesc() {
		return eqStatusDesc;
	}
	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}