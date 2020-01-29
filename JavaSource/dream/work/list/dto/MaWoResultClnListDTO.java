package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 청소설비 목록 DTO
 * @author  kim21017
 * @version $Id: MaWoResultClnListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultClnListDTO extends BaseDTO
{
	/** 작업설비 id */
	private String woEquipId 	= "";

	public String getWoEquipId() {
		return woEquipId;
	}

	public void setWoEquipId(String woEquipId) {
		this.woEquipId = woEquipId;
	}
}