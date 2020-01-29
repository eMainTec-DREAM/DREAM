package dream.rcm.taskmap.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� ���� DTO
 * @author  kim21017
 * @version $Id: RcmTaskMapCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class RcmTaskMapCommonDTO extends BaseDTO
{
	/** ¡�� id */
	private String pmTaskMapListId 				= "";

	/** ���� System �м� ��  */
	private String filterDesc			= "";
	

	public String getPmTaskMapListId() {
		return pmTaskMapListId;
	}
	public void setPmTaskMapListId(String pmTaskMapListId) {
		this.pmTaskMapListId = pmTaskMapListId;
		super.setAuditKey(pmTaskMapListId);
	}
	public String getFilterDesc() {
		return filterDesc;
	}
	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}

}
