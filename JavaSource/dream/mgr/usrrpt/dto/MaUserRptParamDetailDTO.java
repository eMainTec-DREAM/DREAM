package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptParamDetailDTO extends BaseDTO
{
	/** �� */
	private String conValue = "";
	/** ���� ������ */
	private String whrConOperator = "";
	/** ���� ������ */
	private String whrConOperatorDesc = "";
	/** �����REPORT �÷��׸�ID */
	private String usrrptwhrId = "";
	/** �÷����� */
	private String colDesc	= "";
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