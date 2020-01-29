package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * 평가항목 - 공통 DTO
 * @author kim21017
 * @version $Id: AssBasePointListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBasePointListDTO extends BaseDTO
{
	/**   평가기준 ID */
	private String assBaseListId			= "";
	/**   평가항목 ID*/
	private String assBasePointId 			= "";
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getAssBasePointId() {
		return assBasePointId;
	}
	public void setAssBasePointId(String assBasePointId) {
		this.assBasePointId = assBasePointId;
		super.setAuditKey(assBasePointId);
	}
	
	
}
