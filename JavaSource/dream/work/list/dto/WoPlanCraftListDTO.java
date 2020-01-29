package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획목록 - 작업자 목록 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WoPlanCraftListDTO extends BaseDTO
{
	/** 작업계획목록 작업자 id */
	private String woCraftId 	= "";

	public String getWoCraftId() {
		return woCraftId;
	}

	public void setWoCraftId(String woCraftId) {
		this.woCraftId = woCraftId;
	}
	
}