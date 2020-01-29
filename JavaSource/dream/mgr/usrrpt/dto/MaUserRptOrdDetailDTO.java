package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptOrdDetailDTO extends BaseDTO
{
	/** ���Ĺ�� */
	private String sortType = "";
	
	private String sortTypeDesc	= "";
	/** �����REPORT ORD ID */
	private String usrrptordId = "";
	
	/** ȭ��Alignment */
	private String colAlign = "";
	
	private String colAlignDesc = "";
	/** ȭ��Width */
	private String colWidth = "";
	/** ȭ�� Font Size */
	private String colSize = "";
	/** �÷����� */
	private String colDesc	= "";
	/** ȭ��ǥ�ø�[ALIAS] */
	private String colAlias = "";
	/** �÷��� */
	private String columnName = "";
	/** ���̺��÷� ID */
	private String tabcolId = "";
	/** ���̺� */
	private String tableName = "";
	/** ���̺� ID */
	private String tableId = "";
	/** ���� */
	private String stepNum = "";
	/** ����� Report ����Ʈ id */
	private String usrrptlistId = "";
	/** �����REPORT ���̺�ID */
	private String usrrpttabId = "";
	/** �����REPORT �÷��׸�ID */
	private String usrrptcolId = "";
	/** ȸ���ڵ� */
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