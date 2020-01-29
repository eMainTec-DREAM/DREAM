package dream.part.adj.stktake.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ�ǻ� ��ǰ   DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class PartAdjStkTakeListDTO extends BaseDTO
{
	/** �����ûItem ID */
	private String ptStkTakeItemId 	= "";
	
	private String ptStkTakeStatus 	= "";

	
	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}

	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}

	public String getPtStkTakeItemId() {
		return ptStkTakeItemId;
	}

	public void setPtStkTakeItemId(String ptStkTakeItemId) {
		this.ptStkTakeItemId = ptStkTakeItemId;
		super.setAuditKey(ptStkTakeItemId);
	}

	
	
	
}