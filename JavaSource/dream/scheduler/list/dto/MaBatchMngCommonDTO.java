package dream.scheduler.list.dto;

import common.bean.BaseDTO;

/**
 * 배치작업내역 공통 DTO
 * @author  kim21017
 * @version $Id: MaBatchMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaBatchMngCommonDTO extends BaseDTO
{
	/**  ID */
	private String batPgmId 			= "";
	/** 필터 설명 */
	private String filterDesc 			= "";
	
	public String getBatPgmId() {
		return batPgmId;
	}
	public void setBatPgmId(String batPgmId) {
		this.batPgmId = batPgmId;
		super.setAuditKey(batPgmId);
	}
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}

}
