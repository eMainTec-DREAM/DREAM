package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 투입자재 목록 DTO
 * @author  kim21017
 * @version $Id: MaWoResultPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultPartListDTO extends BaseDTO
{
	/** 작업결과 투입자재 id */
	private String woPartId 	= "";
	private String equipId      = "";
	private String equipDesc    = "";
	private String eqAsmbId     = "";
	private String eqAsmbDesc   = "";
	
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

	public String getWoPartId() {
		return woPartId;
	}

    public void setWoPartId(String woPartId) {
		this.woPartId = woPartId;
	}

}