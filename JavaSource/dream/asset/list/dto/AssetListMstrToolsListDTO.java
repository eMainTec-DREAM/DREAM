package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비 구성자재 목록 dto
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class AssetListMstrToolsListDTO extends BaseDTO
{
	/** 구성자재 id */
	private String equipId 	= "";

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	

}