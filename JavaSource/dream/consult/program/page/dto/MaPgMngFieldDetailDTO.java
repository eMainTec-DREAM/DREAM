package dream.consult.program.page.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 화면별 필드 상세 dto
 * @author  kim21017
 * @version $Id: MaPgMngFieldDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngFieldDetailDTO extends BaseDTO
{
	/** PGFIELD_ID */
	private String pgFieldId				= "";
	/** 페이지 아이디 */
	private String pageId					= "";
	/** 화면명 */
	private String pageDesc					= "";
	/** 필드 아이디 */
	private String fieldId					= "";
	/** 라벨 keyType */
	private String keyType					= "";
	/** 라벨 keyNo */
	private String keyNo					= "";
	/** Label 명 */
	private String keyName					= "";
	/** 시스템 숨김여부 */
	private String hiddenYn					= "";
	/** 시스템 숨김여부 DESC */
	private String hiddenYnDesc				= "";
	/** 조회순서 */
	private String ordNo					= "";
	/** 화면 Display 여부 */
	private String displayYn				= "";
	/** 화면 Display 여부 DESC */
	private String displayYnDesc			= "";
	/** 필드설명 */
	private String fieldDesc				= "";
	/** 필수여부 */
	private String checkYn					= "";
	/** 필수여부 DESC */
	private String checkYnDesc				= "";
	/** 필드옵션 */
	private String fieldOption				= "";
	/** 필드옵션명 */
	private String fieldOptionDesc			= "";
	/** 읽기전용 */
	private String readonlyYn				= "";
	/** 읽기전용 DESC */
	private String readonlyYnDesc			= "";
	/** 항목 길이 제한 */
	private String strLength				= "";
	/** 화면입력 타입ID */
	private String formInputTypeId			= "";
	/** 화면입력 타입명 */
	private String formInputTypeDesc		= "";
	/** 화면입력 세부타입ID */
	private String formInputDetailTypeId	= "";
	/** 화면입력 세부타입명 */
	private String formInputDetailTypeDesc	= "";
	/** 코드 유형 ID */
	private String codeListTypeId			= "";
	/** 코드 유형 명 */
	private String codeListTypeDesc			= "";
	/** Validation 체크 스크립트 */
	private String validationJscript		= "";
	
	/** 그룹 Key Type */
	private String groupKeyType				= "";
	/** 그룹 Key No */
	private String groupKeyNo				= "";
	/** 그룹 Key Name */
	private String groupKeyName				= "";
	/** Group 옵션 */
	private String groupOption				= "";
	/** Group 옵션 명 */
	private String groupOptionDesc			= "";
	/** 데이터 테이블 ID */
	private String tableId 					= "";
	/** 데이터 테이블 명 */
	private String tableName				= "";
	/** 데이터 테이블 ID */
	private String tableDesc 				= "";
	/** 데이터 컬럼 명 */
	private String columnName				= "";
	/** 데이터 컬럼 명 */
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