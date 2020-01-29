package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ȭ�麰 �ʵ� �⺻ �� �� dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldValueDetailDTO extends BaseDTO
{
	/** �ʵ� ID */
	private String pgFieldId			= "";
	/** �ʵ� �� ID */
	private String pgFieldValueId		= "";
	/** ȸ�� ID */
	private String compNoId				= "";
	/** ȸ�� �� */
	private String compNoDesc			= "";
	/** ���ϸ� */
	private String fileName				= "";
	/** �ʵ� name */
	private String fieldId				= "";
	/** �⺻ �� ID */
	private String defaultObjectId		= "";
	/** �⺻ �� ID */
	private String defaultValueId		= "";
	/** �⺻ �� ��*/
	private String defaultValueDesc		= "";
	/** �� ���� */
	private String dateInterval			= "";
	
	public String getPgFieldId() {
		return pgFieldId;
	}
	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
	public String getPgFieldValueId() {
		return pgFieldValueId;
	}
	public void setPgFieldValueId(String pgFieldValueId) {
		this.pgFieldValueId = pgFieldValueId;
	}
	public String getCompNoId() {
		return compNoId;
	}
	public void setCompNoId(String compNoId) {
		this.compNoId = compNoId;
	}
	public String getCompNoDesc() {
		return compNoDesc;
	}
	public void setCompNoDesc(String compNoDesc) {
		this.compNoDesc = compNoDesc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getDefaultObjectId() {
		return defaultObjectId;
	}
	public void setDefaultObjectId(String defaultObjectId) {
		this.defaultObjectId = defaultObjectId;
	}
	public String getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(String dateInterval) {
		this.dateInterval = CommonUtil.convertMoney(dateInterval);
	}
	public String getDefaultValueId() {
		return defaultValueId;
	}
	public void setDefaultValueId(String defaultValueId) {
		this.defaultValueId = defaultValueId;
	}
	public String getDefaultValueDesc() {
		return defaultValueDesc;
	}
	public void setDefaultValueDesc(String defaultValueDesc) {
		this.defaultValueDesc = defaultValueDesc;
	}
	
}