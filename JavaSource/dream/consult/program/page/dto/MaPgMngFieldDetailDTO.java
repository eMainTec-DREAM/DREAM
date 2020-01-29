package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ȭ�麰 �ʵ� �� dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldDetailDTO extends BaseDTO
{
	/** PGFIELD_ID */
	private String pgFieldId				= "";
	/** ������ ���̵� */
	private String pageId					= "";
	/** ȭ��� */
	private String pageDesc					= "";
	/** �ʵ� ���̵� */
	private String fieldId					= "";
	/** �� keyType */
	private String keyType					= "";
	/** �� keyNo */
	private String keyNo					= "";
	/** Label �� */
	private String keyName					= "";
	/** �ý��� ���迩�� */
	private String hiddenYn					= "";
	/** �ý��� ���迩�� DESC */
	private String hiddenYnDesc				= "";
	/** ��ȸ���� */
	private String ordNo					= "";
	/** ȭ�� Display ���� */
	private String displayYn				= "";
	/** ȭ�� Display ���� DESC */
	private String displayYnDesc			= "";
	/** �ʵ弳�� */
	private String fieldDesc				= "";
	/** �ʼ����� */
	private String checkYn					= "";
	/** �ʼ����� DESC */
	private String checkYnDesc				= "";
	/** �ʵ�ɼ� */
	private String fieldOption				= "";
	/** �ʵ�ɼǸ� */
	private String fieldOptionDesc			= "";
	/** �б����� */
	private String readonlyYn				= "";
	/** �б����� DESC */
	private String readonlyYnDesc			= "";
	/** �׸� ���� ���� */
	private String strLength				= "";
	/** ȭ���Է� Ÿ��ID */
	private String formInputTypeId			= "";
	/** ȭ���Է� Ÿ�Ը� */
	private String formInputTypeDesc		= "";
	/** ȭ���Է� ����Ÿ��ID */
	private String formInputDetailTypeId	= "";
	/** ȭ���Է� ����Ÿ�Ը� */
	private String formInputDetailTypeDesc	= "";
	/** �ڵ� ���� ID */
	private String codeListTypeId			= "";
	/** �ڵ� ���� �� */
	private String codeListTypeDesc			= "";
	/** Validation üũ ��ũ��Ʈ */
	private String validationJscript		= "";
	
	/** �׷� Key Type */
	private String groupKeyType				= "";
	/** �׷� Key No */
	private String groupKeyNo				= "";
	/** �׷� Key Name */
	private String groupKeyName				= "";
	/** Group �ɼ� */
	private String groupOption				= "";
	/** Group �ɼ� �� */
	private String groupOptionDesc			= "";
	/** ������ ���̺� ID */
	private String tableId 					= "";
	/** ������ ���̺� �� */
	private String tableName				= "";
	/** ������ ���̺� ID */
	private String tableDesc 				= "";
	/** ������ �÷� �� */
	private String columnName				= "";
	/** ������ �÷� �� */
	private String columnDesc				= "";
	
	public String getCheckYnDesc() {
		return checkYnDesc;
	}
	public String getHiddenYnDesc() {
		return hiddenYnDesc;
	}
	public void setHiddenYnDesc(String hiddenYnDesc) {
		this.hiddenYnDesc = hiddenYnDesc;
	}
	public String getDisplayYnDesc() {
		return displayYnDesc;
	}
	public void setDisplayYnDesc(String displayYnDesc) {
		this.displayYnDesc = displayYnDesc;
	}
	public String getReadonlyYnDesc() {
		return readonlyYnDesc;
	}
	public void setReadonlyYnDesc(String readonlyYnDesc) {
		this.readonlyYnDesc = readonlyYnDesc;
	}
	public void setCheckYnDesc(String checkYnDesc) {
		this.checkYnDesc = checkYnDesc;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getColumnDesc() {
		return columnDesc;
	}
	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getGroupOption() {
		return groupOption;
	}
	public void setGroupOption(String groupOption) {
		this.groupOption = groupOption;
	}
	public String getGroupOptionDesc() {
		return groupOptionDesc;
	}
	public void setGroupOptionDesc(String groupOptionDesc) {
		this.groupOptionDesc = groupOptionDesc;
	}
	public String getGroupKeyType() {
		return groupKeyType;
	}
	public void setGroupKeyType(String groupKeyType) {
		this.groupKeyType = groupKeyType;
	}
	public String getGroupKeyNo() {
		return groupKeyNo;
	}
	public void setGroupKeyNo(String groupKeyNo) {
		this.groupKeyNo = groupKeyNo;
	}
	public String getGroupKeyName() {
		return groupKeyName;
	}
	public void setGroupKeyName(String groupKeyName) {
		this.groupKeyName = groupKeyName;
	}
	public String getCodeListTypeId() {
		return codeListTypeId;
	}
	public void setCodeListTypeId(String codeListTypeId) {
		this.codeListTypeId = codeListTypeId;
	}
	public String getCodeListTypeDesc() {
		return codeListTypeDesc;
	}
	public void setCodeListTypeDesc(String codeListTypeDesc) {
		this.codeListTypeDesc = codeListTypeDesc;
	}
	public String getValidationJscript() {
		return validationJscript;
	}
	public void setValidationJscript(String validationJscript) {
		this.validationJscript = validationJscript;
	}
	public String getFormInputDetailTypeId() {
		return formInputDetailTypeId;
	}
	public void setFormInputDetailTypeId(String formInputDetailTypeId) {
		this.formInputDetailTypeId = formInputDetailTypeId;
	}
	public String getFormInputDetailTypeDesc() {
		return formInputDetailTypeDesc;
	}
	public void setFormInputDetailTypeDesc(String formInputDetailTypeDesc) {
		this.formInputDetailTypeDesc = formInputDetailTypeDesc;
	}
	public String getFormInputTypeId() {
		return formInputTypeId;
	}
	public void setFormInputTypeId(String formInputTypeId) {
		this.formInputTypeId = formInputTypeId;
	}
	public String getFormInputTypeDesc() {
		return formInputTypeDesc;
	}
	public void setFormInputTypeDesc(String formInputTypeDesc) {
		this.formInputTypeDesc = formInputTypeDesc;
	}
	public String getStrLength() {
		return strLength;
	}
	public void setStrLength(String strLength) {
		this.strLength = CommonUtil.convertMoney(strLength);
	}
	public String getPageDesc()
    {
        return pageDesc;
    }
    public void setPageDesc(String pageDesc)
    {
        this.pageDesc = pageDesc;
    }
    public String getReadonlyYn()
    {
        return readonlyYn;
    }
    public void setReadonlyYn(String readonlyYn)
    {
        this.readonlyYn = readonlyYn;
    }
    public String getFieldOption() {
		return fieldOption;
	}
	public void setFieldOption(String fieldOption) {
		this.fieldOption = fieldOption;
	}
	public String getFieldOptionDesc() {
		return fieldOptionDesc;
	}
	public void setFieldOptionDesc(String fieldOptionDesc) {
		this.fieldOptionDesc = fieldOptionDesc;
	}
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getPgFieldId() {
		return pgFieldId;
	}
	public void setPgFieldId(String pgFieldId) {
		this.pgFieldId = pgFieldId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
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
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getHiddenYn() {
		return hiddenYn;
	}
	public void setHiddenYn(String hiddenYn) {
		this.hiddenYn = hiddenYn;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getDisplayYn() {
		return displayYn;
	}
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	
}