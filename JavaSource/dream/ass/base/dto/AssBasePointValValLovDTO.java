package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * 평가항목 평가기준 LOV DTO
 * @author kim21017
 * @version $Id: AssBasePointValValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBasePointValValLovDTO extends BaseDTO
{
	/**   평가기준 ID */
	private String assBasePvalId			= "";
	/**   평가기준 명 */
	private String filterAssEval			= "";
	
	public String getAssBasePvalId() {
		return assBasePvalId;
	}
	public void setAssBasePvalId(String assBasePvalId) {
		this.assBasePvalId = assBasePvalId;
	}
	public String getFilterAssEval() {
		return filterAssEval;
	}
	public void setFilterAssEval(String filterAssEval) {
		this.filterAssEval = filterAssEval;
	}
	
	
}
