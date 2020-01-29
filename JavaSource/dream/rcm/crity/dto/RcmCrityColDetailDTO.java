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
	/**Criticality ����Ʈ ID*/
	private String crityListId 			= "";
	/**Criticality Col ����ƮID*/
	private String crityColId	 		= "";
	/**Criticality Col ��*/
	private String crityColDesc	 		= "";
	/**��ȸ����*/
	private String ordNo 				= "";
	/**��� */
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
