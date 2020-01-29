package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비변경이력 공통 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrHistListDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqMstrHistListDTO extends BaseDTO
{
	private String eqalthistId				= "";
	/** 설비ID */
	private String equipId					= "";
	/** 설비No */
	private String itemNo					= "";
	/** 설비명 */
	private String equipDesc				= "";
	/** 필터-변경FROM일자 */
	private String filterChangeFromDate		= "";
	/** 필터-변경TO일자 */
	private String filterChangeToDate		= "";
	
	
	public String getEqalthistId() {
		return eqalthistId;
	}
	public void setEqalthistId(String eqalthistId) {
		this.eqalthistId = eqalthistId;
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
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getFilterChangeFromDate() {
		return filterChangeFromDate;
	}
	public void setFilterChangeFromDate(String filterChangeFromDate) {
		this.filterChangeFromDate = filterChangeFromDate;
	}
	public String getFilterChangeToDate() {
		return filterChangeToDate;
	}
	public void setFilterChangeToDate(String filterChangeToDate) {
		this.filterChangeToDate = filterChangeToDate;
	}
	
}
