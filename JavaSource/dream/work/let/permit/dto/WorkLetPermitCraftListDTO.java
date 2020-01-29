package dream.work.let.permit.dto;

import common.bean.BaseDTO;

/**
 * 안전작업허가서 작업유형 - 작업자 목록 DTO
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class WorkLetPermitCraftListDTO extends BaseDTO
{
	/** 안전작업허가서 작업자 id */
	private String woLetListCraftId 	= "";

	
	public String getWoLetListCraftId() {
		return woLetListCraftId;
	}

	public void setWoLetListCraftId(String woLetListCraftId) {
		this.woLetListCraftId = woLetListCraftId;
		super.setAuditKey(woLetListCraftId);
	}
	
}