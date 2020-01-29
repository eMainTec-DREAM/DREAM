package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * ���������� �����׸� ���� list DTO
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListDTO.java,v 1.1 2015/12/04 09:10:45 euna0207 Exp $
 * @since   1.0
 */
public class MaEqCatalogPointListDTO extends BaseDTO
{
	/** ���������� �����׸��ڵ� (key)*/
	private String eqCtgPmPointId = "";
	/** ���������ڵ� ID */
	private String eqCtgId 		  = "";
	/** ���������ȣ */
	private String eqasmbId 	  = "";
    /** �����׸� �� ������  */


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