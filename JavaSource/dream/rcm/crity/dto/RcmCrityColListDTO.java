package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Col Page - 공통 DTO
 * @author kim21017
 * @version $Id: RcmCrityColListDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityColListDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 				= "";
	/**Criticality Col ID*/
	private String crityColId 				= "";
	
	public String getCrityColId() {
		return crityColId;
	}
	public void setCrityColId(String crityColId) {
		this.crityColId = crityColId;
		super.setAuditKey(crityColId);
	}
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	
}
