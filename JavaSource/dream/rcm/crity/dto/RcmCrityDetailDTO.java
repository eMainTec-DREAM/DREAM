package dream.rcm.crity.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Criticality Matrix Page - Detail DTO
 * @author kim21017
 * @version $Id: RcmCrityDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityDetailDTO extends BaseDTO
{
	/**Criticality ����Ʈ ID*/
	private String crityListId 			= "";
	/**Criticality ����Ʈ NO*/
	private String crityListNo 			= "";
	/**Criticality ����Ʈ ��*/
	private String crityListDesc 		= "";
	/**��뿩�� ID*/
	private String isUseId 				= "";
	/**��뿩�� */
	private String isUseDesc			= "";
	/**����� */
	private String regDate				= "";
	/**��� */
	private String remark				= "";
	
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
		super.setAuditKey(crityListId);
	}
	public String getCrityListNo() {
		return crityListNo;
	}
	public void setCrityListNo(String crityListNo) {
		this.crityListNo = crityListNo;
	}
	public String getCrityListDesc() {
		return crityListDesc;
	}
	public void setCrityListDesc(String crityListDesc) {
		this.crityListDesc = crityListDesc;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
