package dream.consult.program.btn.dto;

import common.bean.BaseDTO;

/**
 * ��ư - �� DTO
 * @author  kim21017
 * @version $Id: MaBtnMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaBtnMngDetailDTO extends BaseDTO
{
	/** ��ưID */
	private String buttonId 			= "";
	/** ��ư�� */
	private String buttonNo 			= "";
	/** ��ư�� */
	private String buttonDesc 			= "";
	/** ��ư������ */
	private String btnImg	 			= "";
	/** ��뿩�� */
	private String isUse 				= "";
	/** ��ȸ���� */
	private String ordNo 				= "";
	/** ��� */
	private String remark 				= "";
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getButtonId() {
		return buttonId;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public String getButtonNo() {
		return buttonNo;
	}
	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}
	public String getButtonDesc() {
		return buttonDesc;
	}
	public void setButtonDesc(String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}
	public String getBtnImg() {
		return btnImg;
	}
	public void setBtnImg(String btnImg) {
		this.btnImg = btnImg;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}
