package dream.invt.prc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매절차 공통 DTO
 * @author  kim21017
 * @version $Id: InvtPrcTpCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class InvtPrcTpCommonDTO extends BaseDTO
{
	/** 구매절차 공통 id */
	private String invtPrcTpId 				= "";

	/** 필터 구매절차 명  */
	private String filterInvtDesc			= "";

	public String getInvtPrcTpId() {
		return invtPrcTpId;
	}

	public void setInvtPrcTpId(String invtPrcTpId) {
		this.invtPrcTpId = invtPrcTpId;
		super.setAuditKey(invtPrcTpId);
	}

	public String getFilterInvtDesc() {
		return filterInvtDesc;
	}

	public void setFilterInvtDesc(String filterInvtDesc) {
		this.filterInvtDesc = filterInvtDesc;
	}
	
	

	
}
