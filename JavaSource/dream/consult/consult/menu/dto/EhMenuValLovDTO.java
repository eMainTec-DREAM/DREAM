package dream.consult.consult.menu.dto;

import common.bean.BaseDTO;
/**
 * 컨설트 메뉴 LOV DTO
 * @author kim21017
 * @version $Id: EhMenuValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class EhMenuValLovDTO extends BaseDTO
{
	/**   메뉴 ID */
	private String ehMenuId				= "";
	/**   메뉴 명 */
	private String filterEhMenuDesc		= "";
	
	public String getEhMenuId() {
		return ehMenuId;
	}
	public void setEhMenuId(String ehMenuId) {
		this.ehMenuId = ehMenuId;
	}
	public String getFilterEhMenuDesc() {
		return filterEhMenuDesc;
	}
	public void setFilterEhMenuDesc(String filterEhMenuDesc) {
		this.filterEhMenuDesc = filterEhMenuDesc;
	}
	
	
}
