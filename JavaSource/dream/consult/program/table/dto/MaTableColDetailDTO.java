
package dream.consult.program.table.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ���̺� - �з�  DTO
 * @author  kim21017
 * @version $Id: MaTableColDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaTableColDetailDTO extends BaseDTO
{
	/** ������ ���̺�������ID */
	private String tabColId 	= "";
	/** �÷� */
	private String columnName			= "";
	/** �÷��� */
	private String description			= "";
	/** Ÿ�� */
	private String dataTypeDesc			= "";
	/** Ÿ�� */
	private String dataTypeId			= "";
	/** ���� */
	private String dataLength			= "";
	/** ������ */
	private String creByDesc			= "";
	/** ������ */
	private String creBy			= "";
	/** �������� */
	private String creDate			= "";
	/** �󼼼��� */
	private String remark			= "";
	/** ref���̺� */
	private String refTable			= "";
	/** ref�÷� */
	private String refColumn			= "";
	/** ������ ���̺�������ID */
	private String refTabColId 	= "";
	/** ���� */
	private String seqNo 	= "";
    /** �ٱ��� keyno */
	private String keyNo			= "";
	/** �ٱ��� keytype */
	private String keyType			= "";
	//key_no validation �� �ѱ�� key_type
	private String keyTypeStr		= "";
    
    
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getRefTabColId() {
		return refTabColId;
	}
	public void setRefTabColId(String refTabColId) {
		this.refTabColId = refTabColId;
	}
	public String getRefTable() {
		return refTable;
	}
	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}
	public String getRefColumn() {
		return refColumn;
	}
	public void setRefColumn(String refColumn) {
		this.refColumn = refColumn;
	}
	public String getTabColId() {
		return tabColId;
	}
	public void setTabColId(String tabColId) {
		this.tabColId = tabColId;
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDataTypeDesc() {
		return dataTypeDesc;
	}
	public void setDataTypeDesc(String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}
	public String getDataTypeId() {
		return dataTypeId;
	}
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getCreByDesc() {
		return creByDesc;
	}
	public void setCreByDesc(String creByDesc) {
		this.creByDesc = creByDesc;
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDate(creDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyTypeStr() {
		return keyTypeStr;
	}
	public void setKeyTypeStr(String keyTypeStr) {
		this.keyTypeStr = keyTypeStr;
	}
	
}