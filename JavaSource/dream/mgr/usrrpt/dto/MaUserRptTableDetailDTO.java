package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * �� dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaUserRptTableDetailDTO extends BaseDTO
{
	/** JOIN TYPE */
	private String joinType 	= "";
	
	private String joinTypeDesc	= "";
	/** Main,Sub���̺� ���� */
	private String mainSubType 	= "";
	/** ���̺� */
	private String tableName 	= "";
	
	private String tableDesc	= "";
	/** ���̺� ID */
	private String tableId 		= "";
	/** ���� */
	private String stepNum 		= "";
	/** ����� Report ����Ʈ id */
	private String usrrptlistId = "";
	/** �����REPORT ���̺�ID */
	private String usrrpttabId 	= "";
	/** ȸ���ڵ� */
	private String compNo 		= "";
	
	
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getJoinType() {
		return joinType;
	}
	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}
	public String getJoinTypeDesc() {
		return joinTypeDesc;
	}
	public void setJoinTypeDesc(String joinTypeDesc) {
		this.joinTypeDesc = joinTypeDesc;
	}
	public String getMainSubType() {
		return mainSubType;
	}
	public void setMainSubType(String mainSubType) {
		this.mainSubType = mainSubType;
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
		super.setAuditKey(usrrpttabId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
   }