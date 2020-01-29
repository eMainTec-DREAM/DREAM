package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * ȭ�麰 �������� �� dto
 * @author  kim21017
 * @version $Id: MaPgMngPageDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngPageDetailDTO extends BaseDTO
{
	/** ��������_ID(������) */
	private String pgPageId				= "";
	/** �θ�ȭ��� */
	private String pgDesc				= "";
	/** �������� ȭ��id*/
	private String pageId				= "";
	/** �������� ȭ��� */
	private String pageDesc				= "";
	/** ��뿩�� */
	private String isUse				= "";
	/** ��ȸ���� */
	private String ordNo				= "";
	/** �ٱ���KEY_TYPE */
	private String keyType				= "";
	/** �ٱ���KEY_NO */
	private String keyNo				= "";
	/** �ٱ���� */
	private String langDesc				= "";
	/** ���ϸ� */
	private String fileName				= "";
	/** �������� ���� */
	private String remark				= "";
	
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public String getPgPageId() {
		return pgPageId;
	}
	public void setPgPageId(String pgPageId) {
		this.pgPageId = pgPageId;
	}
	public String getPgDesc() {
		return pgDesc;
	}
	public void setPgDesc(String pgDesc) {
		this.pgDesc = pgDesc;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
}