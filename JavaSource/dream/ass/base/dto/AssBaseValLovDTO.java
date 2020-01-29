package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * 설비등급 평가기준 LOV DTO
 * @author kim21017
 * @version $Id: AssBaseValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBaseValLovDTO extends BaseDTO
{
	/**   평가기준 ID */
	private String assBaseListId			= "";
	/**   평가기준 명 */
	private String filterAssBaseDesc		= "";
	
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getFilterAssBaseDesc() {
		return filterAssBaseDesc;
	}
	public void setFilterAssBaseDesc(String filterAssBaseDesc) {
		this.filterAssBaseDesc = filterAssBaseDesc;
	}
	
}
