package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾���� �۾��� ��� DTO
 * @author  kim21017
 * @version $Id: MaWoResultCraftListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultCraftListDTO extends BaseDTO
{
	/** �۾���� �۾��� id */
	private String woCraftId 	= "";

	public String getWoCraftId() {
		return woCraftId;
	}

	public void setWoCraftId(String woCraftId) {
		this.woCraftId = woCraftId;
		super.setAuditKey(woCraftId);
	}
	
}