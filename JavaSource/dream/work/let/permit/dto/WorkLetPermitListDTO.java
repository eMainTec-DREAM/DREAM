package dream.work.let.permit.dto;

import common.bean.BaseDTO;

/**
 * 안전작업 - 안전작업허가서 작업유형 목록 DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WorkLetPermitListDTO extends BaseDTO
{
	/** 안전작업허가서 유형 ID */
	private String woLetListId 	= "";
	
	
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
		super.setAuditKey(woLetListId);
	}
	
}