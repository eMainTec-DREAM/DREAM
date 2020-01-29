
package dream.consult.program.table.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 데이터 테이블 - 분류  DTO
 * @author  kim21017
 * @version $Id: MaTableColDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaTableColDetailDTO extends BaseDTO
{
	/** 데이터 테이블유형상세ID */
	private String tabColId 	= "";
	/** 컬럼 */
	private String columnName			= "";
	/** 컬럼명 */
	private String description			= "";
	/** 타입 */
	private String dataTypeDesc			= "";
	/** 타입 */
	private String dataTypeId			= "";
	/** 길이 */
	private String dataLength			= "";
	/** 생성자 */
	private String creByDesc			= "";
	/** 생성자 */
	private String creBy			= "";
	/** 생성일자 */
	private String creDate			= "";
	/** 상세설명 */
	private String remark			= "";
	/** ref테이블 */
	private String refTable			= "";
	/** ref컬럼 */
	private String refColumn			= "";
	/** 데이터 테이블유형상세ID */
	private String refTabColId 	= "";
	/** 순번 */
	private String seqNo 	= "";
    /** 다국어 keyno */
	private String keyNo			= "";
	/** 다국어 keytype */
	private String keyType			= "";
	//key_no validation 시 넘기는 key_type
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