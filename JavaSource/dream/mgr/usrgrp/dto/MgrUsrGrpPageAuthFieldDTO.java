package dream.mgr.usrgrp.dto;

import common.bean.BaseDTO;
/**
 * ȭ����Ѽ������ǹ�ư����
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class MgrUsrGrpPageAuthFieldDTO extends BaseDTO
{
	/**ȭ�� ID*/
	private String pageId                  	= "";
	/**���ѱ׷� ID*/
	private String usrgrpId                	= "";
	/**�ʵ���� ID (key) */
	private String ugpgfieldauId          	= "";
	private String pgfieldId				= "";
	/**�ʵ� ID*/
	private String fieldId                 	= "";
	/**�ʵ��*/
	private String labelDesc                = "";
	/**��������*/
	private String authLimitTypeId	        = "";
	private String authLimitTypeDesc        = "";
	/**�ʼ�����*/
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
