package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별 표준제원 목록 dto
 * @author  syyang
 * @version $Id: MaEqCtgAsmbListDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class MaEqCtgSpecListDTO extends BaseDTO
{
	/** 설비종류별 설비표준제원 id */
	private String eqCtgSpecId 	= "";

	
	public String getEqCtgSpecId() {
		return eqCtgSpecId;
	}

	public void setEqCtgSpecId(String eqCtgSpecId) {
		this.eqCtgSpecId = eqCtgSpecId;
	}
	
	
}