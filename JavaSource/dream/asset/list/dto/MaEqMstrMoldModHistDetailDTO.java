package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비 구성자재 상세 dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrMoldModHistDetailDTO extends BaseDTO
{
	/**  id */
	private String eqMoldModHistId			= "";
	/** 설비ID */
	private String equipId			= "";
	/** 차수 */
	private String modCnt			= "";
	/** Cavity */
	private String capacity			= "";
	/** 일생산량 */
	private String outPut		  = "";
	/** 소유권 */
	private String ownerShip			= "";
	/** 비고 */
	private String remark		= "";
	/** 조회순서 */
	private String ordNo		= "";
	
	
	public String getEqMoldModHistId() {
		return eqMoldModHistId;
	}
	public void setEqMoldModHistId(String eqMoldModHistId) {
		this.eqMoldModHistId = eqMoldModHistId;
		super.setAuditKey(eqMoldModHistId);
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getModCnt() {
		return modCnt;
	}
	public void setModCnt(String modCnt) {
		this.modCnt = CommonUtil.convertMoney(modCnt);
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getOutPut() {
		return outPut;
	}
	public void setOutPut(String outPut) {
		this.outPut = CommonUtil.convertMoney(outPut);
	}
	public String getOwnerShip() {
		return ownerShip;
	}
	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	
	
	
	
	
	
	
}