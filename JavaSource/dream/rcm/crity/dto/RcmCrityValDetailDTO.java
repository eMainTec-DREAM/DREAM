package dream.rcm.crity.dto;

import common.bean.BaseDTO;

/**
 * Criticality Matrix Val Page - Detail DTO
 * @author kim21017
 * @version $Id: RcmCrityValDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityValDetailDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 			= "";
	/**Criticality Val 리스트ID*/
	private String crityValId	 		= "";
	/**Criticality Val 명*/
	private String crityValDesc 		= "";
	/**Criticality Col ID*/
	private String crityColId	 		= "";
	/**Criticality Col 명*/
	private String crityColDesc	 		= "";
	/**Criticality Row ID*/
	private String crityRowId	 		= "";
	/**Criticality Row 명*/
	private String crityRowDesc	 		= "";
	/**색상Id */
	private String crityColorId			= "";
	/**색상명 */
	private String crityColorDesc		= "";
	/**critical 여부ID */
	private String isCriticalId			= "";
	/**critical 여부명 */
	private String isCriticalDesc		= "";
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
	public String getCrityValId() {
		return crityValId;
	}
	public void setCrityValId(String crityValId) {
		this.crityValId = crityValId;
		super.setAuditKey(crityValId);
	}
	public String getCrityValDesc() {
		return crityValDesc;
	}
	public void setCrityValDesc(String crityValDesc) {
		this.crityValDesc = crityValDesc;
	}
	public String getCrityColId() {
		return crityColId;
	}
	public void setCrityColId(String crityColId) {
		this.crityColId = crityColId;
	}
	public String getCrityColDesc() {
		return crityColDesc;
	}
	public void setCrityColDesc(String crityColDesc) {
		this.crityColDesc = crityColDesc;
	}
	public String getCrityRowId() {
		return crityRowId;
	}
	public void setCrityRowId(String crityRowId) {
		this.crityRowId = crityRowId;
	}
	public String getCrityRowDesc() {
		return crityRowDesc;
	}
	public void setCrityRowDesc(String crityRowDesc) {
		this.crityRowDesc = crityRowDesc;
	}
	
	public String getCrityColorId() {
		return crityColorId;
	}
	public void setCrityColorId(String crityColorId) {
		this.crityColorId = crityColorId;
	}
	public String getCrityColorDesc() {
		return crityColorDesc;
	}
	public void setCrityColorDesc(String crityColorDesc) {
		this.crityColorDesc = crityColorDesc;
	}
	public String getIsCriticalId() {
		return isCriticalId;
	}
	public void setIsCriticalId(String isCriticalId) {
		this.isCriticalId = isCriticalId;
	}
	public String getIsCriticalDesc() {
		return isCriticalDesc;
	}
	public void setIsCriticalDesc(String isCriticalDesc) {
		this.isCriticalDesc = isCriticalDesc;
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
