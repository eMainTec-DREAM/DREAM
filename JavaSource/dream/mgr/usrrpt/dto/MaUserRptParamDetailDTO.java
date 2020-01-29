package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptParamDetailDTO extends BaseDTO
{
	/** 값 */
	private String conValue = "";
	/** 조건 연산자 */
	private String whrConOperator = "";
	/** 조건 연산자 */
	private String whrConOperatorDesc = "";
	/** 사용자REPORT 컬럼항목ID */
	private String usrrptwhrId = "";
	/** 컬럼설명 */
	private String colDesc	= "";
	/** 컬럼명 */
	private String columnName = "";
	/** 테이블컬럼 ID */
	private String tabcolId = "";
	/** 테이블 */
	private String tableName = "";
	/** 테이블 ID */
	private String tableId = "";
	/** 순서 */
	private String stepNum = "";
	/** 사용자 Report 리스트 id */
	private String usrrptlistId = "";
	/** 사용자REPORT 테이블ID */
	private String usrrpttabId = "";
	/** 사용자REPORT 컬럼항목ID */
	private String usrrptcolId = "";
	/** 회사코드 */
	private String compNo = "";
	
	private String isDisplay	= "";
	
	private String isDisplayDesc	= "";
	
	private String title	= "";
	
	private String colAliasDesc	= "";
	
	private String useYn		= "";

	public String getConValue() {
		return conValue;
	}

	public void setConValue(String conValue) {
		this.conValue = conValue;
	}

	public String getWhrConOperator() {
		return whrConOperator;
	}

	public void setWhrConOperator(String whrConOperator) {
		this.whrConOperator = whrConOperator;
	}

	public String getWhrConOperatorDesc() {
		return whrConOperatorDesc;
	}

	public void setWhrConOperatorDesc(String whrConOperatorDesc) {
		this.whrConOperatorDesc = whrConOperatorDesc;
	}

	public String getUsrrptwhrId() {
		return usrrptwhrId;
	}

	public void setUsrrptwhrId(String usrrptwhrId) {
		this.usrrptwhrId = usrrptwhrId;
	}

	public String getColDesc() {
		return colDesc;
	}

	public void setColDesc(String colDesc) {
		this.colDesc = colDesc;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTabcolId() {
		return tabcolId;
	}

	public void setTabcolId(String tabcolId) {
		this.tabcolId = tabcolId;
		super.setAuditKey(tabcolId);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}

	public String getUsrrptlistId() {
		return usrrptlistId;
	}

	public void setUsrrptlistId(String usrrptlistId) {
		this.usrrptlistId = usrrptlistId;
	}

	public String getUsrrpttabId() {
		return usrrpttabId;
	}

	public void setUsrrpttabId(String usrrpttabId) {
		this.usrrpttabId = usrrpttabId;
	}

	public String getUsrrptcolId() {
		return usrrptcolId;
	}

	public void setUsrrptcolId(String usrrptcolId) {
		this.usrrptcolId = usrrptcolId;
	}

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getIsDisplayDesc() {
		return isDisplayDesc;
	}

	public void setIsDisplayDesc(String isDisplayDesc) {
		this.isDisplayDesc = isDisplayDesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColAliasDesc() {
		return colAliasDesc;
	}

	public void setColAliasDesc(String colAliasDesc) {
		this.colAliasDesc = colAliasDesc;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	
   }