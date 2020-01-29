package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류의 점검항목 탭의 list DTO
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListDTO.java,v 1.1 2015/12/04 09:10:45 euna0207 Exp $
 * @since   1.0
 */
public class MaEqCatalogPointListDTO extends BaseDTO
{
	/** 설비종류별 점검항목코드 (key)*/
	private String eqCtgPmPointId = "";
	/** 설비종류코드 ID */
	private String eqCtgId 		  = "";
	/** 설비부위번호 */
	private String eqasmbId 	  = "";
    /** 점검항목 상세 페이지  */


	public String getEqCtgPmPointId() {
		return eqCtgPmPointId;
	}
	public void setEqCtgPmPointId(String eqCtgPmPointId) {
		this.eqCtgPmPointId = eqCtgPmPointId;
		super.setAuditKey(eqCtgPmPointId);
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqasmbId() {
		return eqasmbId;
	}
	public void setEqasmbId(String eqasmbId) {
		this.eqasmbId = eqasmbId;
	}
}