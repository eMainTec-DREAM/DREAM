package dream.rcm.crity.dto;

import common.bean.BaseDTO;

/**
 * Criticality Matrix Row Page - Detail DTO
 * @author kim21017
 * @version $Id: RcmCrityRowDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityRowDetailDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 			= "";
	/**Criticality Row 리스트ID*/
	private String crityRowId	 		= "";
	/**Criticality Row 명*/
	private String crityRowDesc	 		= "";
	/**조회순서*/
	private String ordNo 				= "";
	/**비고 */
	private String remark				= "";
	/**중요도 레벨 */
	private String crityLevel			= "";
	
	
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	public String getCrityRowId() {
		return crityRowId;
	}
	public void setCrityRowId(String crityRowId) {
		this.crityRowId = crityRowId;
		super.setAuditKey(crityRowId);
	}
	public String getCrityRowDesc() {
		return crityRowDesc;
	}
	public void setCrityRowDesc(String crityRowDesc) {
		this.crityRowDesc = crityRowDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCrityLevel() {
		return crityLevel;
	}
	public void setCrityLevel(String crityLevel) {
		this.crityLevel = crityLevel;
	}

}
