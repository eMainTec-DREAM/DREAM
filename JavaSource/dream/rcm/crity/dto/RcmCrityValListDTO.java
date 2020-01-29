package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Val Page - 공통 DTO
 * @author kim21017
 * @version $Id: RcmCrityValListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityValListDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 				= "";
	/**Criticality Val ID*/
	private String crityValId 				= "";
	
	public String getCrityValId() {
		return crityValId;
	}
	public void setCrityValId(String crityValId) {
		this.crityValId = crityValId;
		super.setAuditKey(crityValId);
	}
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	
}
