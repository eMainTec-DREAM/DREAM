package dream.rcm.crity.dto;

import common.bean.BaseDTO;

/**
 * Criticality Matrix Col Page - Detail DTO
 * @author kim21017
 * @version $Id: RcmCrityColDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityColDetailDTO extends BaseDTO
{
	/**Criticality 리스트 ID*/
	private String crityListId 			= "";
	/**Criticality Col 리스트ID*/
	private String crityColId	 		= "";
	/**Criticality Col 명*/
	private String crityColDesc	 		= "";
	/**조회순서*/
	private String ordNo 				= "";
	/**비고 */
	private String remark				= "";
	
	
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	public String getCrityColId() {
		return crityColId;
	}
	public void setCrityColId(String crityColId) {
		this.crityColId = crityColId;
		super.setAuditKey(crityColId);
	}
	public String getCrityColDesc() {
		return crityColDesc;
	}
	public void setCrityColDesc(String crityColDesc) {
		this.crityColDesc = crityColDesc;
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

}
