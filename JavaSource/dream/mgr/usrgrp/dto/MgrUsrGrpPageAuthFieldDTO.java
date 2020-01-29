package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthFieldDTO extends BaseDTO
{
	/**화면 ID*/
	private String pageId                  	= "";
	/**권한그룹 ID*/
	private String usrgrpId                	= "";
	/**필드권한 ID (key) */
	private String ugpgfieldauId          	= "";
	private String pgfieldId				= "";
	/**필드 ID*/
	private String fieldId                 	= "";
	/**필드명*/
	private String labelDesc                = "";
	/**권한제한*/
	private String authLimitTypeId	        = "";
	private String authLimitTypeDesc        = "";
	/**필수여부*/
	private String checkYn					= "";
	
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getUsrgrpId() {
		return usrgrpId;
	}
	public void setUsrgrpId(String usrgrpId) {
		this.usrgrpId = usrgrpId;
	}
	public String getUgpgfieldauId() {
		return ugpgfieldauId;
	}
	public void setUgpgfieldauId(String ugpgfieldauId) {
		this.ugpgfieldauId = ugpgfieldauId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getLabelDesc() {
		return labelDesc;
	}
	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}
	public String getAuthLimitTypeDesc() {
		return authLimitTypeDesc;
	}
	public void setAuthLimitTypeDesc(String authLimitTypeDesc) {
		this.authLimitTypeDesc = authLimitTypeDesc;
	}
	public String getAuthLimitTypeId() {
		return authLimitTypeId;
	}
	public void setAuthLimitTypeId(String authLimitTypeId) {
		this.authLimitTypeId = authLimitTypeId;
	}
	public String getPgfieldId() {
		return pgfieldId;
	}
	public void setPgfieldId(String pgfieldId) {
		this.pgfieldId = pgfieldId;
	}
	
}
