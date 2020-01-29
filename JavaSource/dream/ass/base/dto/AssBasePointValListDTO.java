package dream.ass.base.dto;

import common.bean.BaseDTO;
/**
 * �򰡱��� - ���� DTO
 * @author kim21017
 * @version $Id: AssBasePointValListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBasePointValListDTO extends BaseDTO
{
	/**   �򰡱��� ID */
	private String assBaseListId				= "";
	/**   ���׸� ID*/
	private String assBasePointId 				= "";
	/**   �򰡱��� ID*/
	private String assBasePointValId 			= "";
	
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
	}
	public String getAssBasePointValId() {
		return assBasePointValId;
	}
	public void setAssBasePointValId(String assBasePointValId) {
		this.assBasePointValId = assBasePointValId;
		super.setAuditKey(assBasePointValId);
	}
	
	
	
}
