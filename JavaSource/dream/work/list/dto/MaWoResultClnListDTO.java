package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���� û�Ҽ��� ��� DTO
 * @author  kim21017
 * @version $Id: MaWoResultClnListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultClnListDTO extends BaseDTO
{
	/** �۾����� id */
	private String woEquipId 	= "";

	public String getWoEquipId() {
		return woEquipId;
	}

	public void setWoEquipId(String woEquipId) {
		this.woEquipId = woEquipId;
	}
}