package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Row Page - 공통 DTO
 * @author kim21017
 * @version $Id: RcmCrityRowListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityRowListDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 				= "";
	/**Criticality Row ID*/
	private String crityRowId 				= "";
	
	public String getCrityRowId() {
		return crityRowId;
	}
	public void setCrityRowId(String crityRowId) {
		this.crityRowId = crityRowId;
		super.setAuditKey(crityRowId);
	}
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	
}
