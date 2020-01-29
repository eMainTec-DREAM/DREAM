package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;

/**
 * ����   DTO
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmTaskMapItemListDTO extends BaseDTO
{
	/** �亯ID */
	private String pmTaskmapId 	= "";

	public String getPmTaskmapId() {
		return pmTaskmapId;
	}

	public void setPmTaskmapId(String pmTaskmapId) {
		this.pmTaskmapId = pmTaskmapId;
		super.setAuditKey(pmTaskmapId);
	}
}