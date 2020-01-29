package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * ������ �򰡱��� DTO
 * @author kim21017
 * @version $Id: AssBaseCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBaseCommonDTO extends BaseDTO
{
	/**   �򰡱��� ID */
	private String assBaseListId			= "";
	/**Filter  �򰡱��� �� */
	private String filterAssBaseListDesc	= "";
	
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
		super.setAuditKey(assBaseListId);
	}
	public String getFilterAssBaseListDesc() {
		return filterAssBaseListDesc;
	}
	public void setFilterAssBaseListDesc(String filterAssBaseListDesc) {
		this.filterAssBaseListDesc = filterAssBaseListDesc;
	}
}
