package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * 등급기준 - 공통 DTO
 * @author kim21017
 * @version $Id: AssBaseGradeListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBaseGradeListDTO extends BaseDTO
{
	/**   평가기준 ID */
	private String assBaseListId			= "";
	/**   등급기준 ID*/
	private String assBaseGradeId 			= "";
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getAssBaseGradeId() {
		return assBaseGradeId;
	}
	public void setAssBaseGradeId(String assBaseGradeId) {
		this.assBaseGradeId = assBaseGradeId;
		super.setAuditKey(assBaseGradeId);
	}
}
