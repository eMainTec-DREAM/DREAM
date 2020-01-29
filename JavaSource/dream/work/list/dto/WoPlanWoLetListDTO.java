package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업계획목록 - 안전작업 목록 DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WoPlanWoLetListDTO extends BaseDTO
{
	/** WO안전작업유형 id */
	private String woWoLetListId 	= "";

	public String getWoWoLetListId() {
		return woWoLetListId;
	}

	public void setWoWoLetListId(String woWoLetListId) {
		this.woWoLetListId = woWoLetListId;
	}

	
}