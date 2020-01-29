package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * ���������� �۾����� ��� dto
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqCtgAsmbListDTO extends BaseDTO
{
	/** ���������� �۾����� id */
	private String eqCtgAsmbId 		= "";
	/** ������ detail�� �θ� �۾����� id */
	private String detailPasmbId	= "";
	/** ������ detail�� �θ� �۾������� */
	private String detailPasmbDesc	= "";

	public String getDetailPasmbId() {
		return detailPasmbId;
	}

	public void setDetailPasmbId(String detailPasmbId) {
		this.detailPasmbId = detailPasmbId;
	}

	public String getDetailPasmbDesc() {
		return detailPasmbDesc;
	}

	public void setDetailPasmbDesc(String detailPasmbDesc) {
		this.detailPasmbDesc = detailPasmbDesc;
	}

	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}

	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	
	
}