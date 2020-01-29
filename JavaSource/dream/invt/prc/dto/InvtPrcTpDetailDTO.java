package dream.invt.prc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������� - �� DTO
 * @author  kim21017
 * @version $Id: InvtPrcTpDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class InvtPrcTpDetailDTO extends BaseDTO
{
	/** �������� ���� ID */
	private String invtPrcTpId 					= "";
	/** �������� ���� NO */
	private String invtNo 					    = "";
	/** �������� ���� �� */
	private String invtDesc 					= "";
	/** ��뿩�� */
	private String isUse 					     = "";
	/** ����� */
	private String regDate 					     = "";
	/** ��� */
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
