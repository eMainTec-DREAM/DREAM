package dream.invt.prc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매절차 - 상세 DTO
 * @author  kim21017
 * @version $Id: InvtPrcTpDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class InvtPrcTpDetailDTO extends BaseDTO
{
	/** 구매절차 종류 ID */
	private String invtPrcTpId 					= "";
	/** 구매절차 종류 NO */
	private String invtNo 					    = "";
	/** 구매절차 종류 명 */
	private String invtDesc 					= "";
	/** 사용여부 */
	private String isUse 					     = "";
	/** 등록일 */
	private String regDate 					     = "";
	/** 비고 */
	private String remark 					    = "";

    public String getInvtPrcTpId() {
		return invtPrcTpId;
	}

	public void setInvtPrcTpId(String invtPrcTpId) {
		this.invtPrcTpId = invtPrcTpId;
		super.setAuditKey(invtPrcTpId);
	}

	public String getInvtNo() {
		return invtNo;
	}

	public void setInvtNo(String invtNo) {
		this.invtNo = invtNo;
	}

	public String getInvtDesc() {
		return invtDesc;
	}

	public void setInvtDesc(String invtDesc) {
		this.invtDesc = invtDesc;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
