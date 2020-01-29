package dream.invt.prc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������� ���� DTO
 * @author  kim21017
 * @version $Id: InvtPrcTpCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class InvtPrcTpCommonDTO extends BaseDTO
{
	/** �������� ���� id */
	private String invtPrcTpId 				= "";

	/** ���� �������� ��  */
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
