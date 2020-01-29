package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptOrdDetailDTO extends BaseDTO
{
	/** 정렬방법 */
	private String sortType = "";
	
	private String sortTypeDesc	= "";
	/** 사용자REPORT ORD ID */
	private String usrrptordId = "";
	
	/** 화면Alignment */
	private String colAlign = "";
	
	private String colAlignDesc = "";
	/** 화면Width */
	private String colWidth = "";
	/** 화면 Font Size */
	private String colSize = "";
	/** 컬럼설명 */
	private String colDesc	= "";
	/** 화면표시명[ALIAS] */
	private String colAlias = "";
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

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortTypeDesc() {
		return sortTypeDesc;
	}

	public void setSortTypeDesc(String sortTypeDesc) {
		this.sortTypeDesc = sortTypeDesc;
	}

	public String getUsrrptordId() {
		return usrrptordId;
	}

	public void setUsrrptordId(String usrrptordId) {
		this.usrrptordId = usrrptordId;
	}

	public String getColAlign() {
		return colAlign;
	}

	public void setColAlign(String colAlign) {
		this.colAlign = colAlign;
	}

	public String getColAlignDesc() {
		return colAlignDesc;
	}

	public void setColAlignDesc(String colAlignDesc) {
		this.colAlignDesc = colAlignDesc;
	}

	public String getColWidth() {
		return colWidth;
	}

	public void setColWidth(String colWidth) {
		this.colWidth = colWidth;
	}

	public String getColSize() {
		return colSize;
	}

	public void setColSize(String colSize) {
		this.colSize = colSize;
	}

	public String getColDesc() {
		return colDesc;
	}

	public void setColDesc(String colDesc) {
		this.colDesc = colDesc;
	}

	public String getColAlias() {
		return colAlias;
	}

	public void setColAlias(String colAlias) {
		this.colAlias = colAlias;
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