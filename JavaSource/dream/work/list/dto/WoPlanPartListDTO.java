package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업계획목록 - 투입부품 목록 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WoPlanPartListDTO extends BaseDTO
{
	/** 작업계획목록 투입부품 id */
	private String woPartId 	= "";

	public String getWoPartId() {
		return woPartId;
	}

	public void setWoPartId(String woPartId) {
		this.woPartId = woPartId;
	}
	
}