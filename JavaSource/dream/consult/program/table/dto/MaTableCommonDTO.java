package dream.consult.program.table.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 데이터 테이블 공통 DTO
 * @author  kim21017
 * @version $Id: MaTableCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaTableCommonDTO extends BaseDTO
{
	/** 데이터 테이블유형상세ID */
	private String tableMId 				= "";
	/** 필터 테이블 */
	private String filterTable 			= "";
	/** 필터 테이블 명 */
	private String filterTableDesc		= "";
	/** 필터 상세설명 */
	private String filterDescription		= "";
	/** 필터 코드 분류 */
	private String paramListTypeCateg		= "";
	
	private String creSdate					= "";
	
	private String creEdate					= "";
	/** 데이터 테이블유형상세ID */
	private String tableName 				= "";
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCreSdate() {
		return creSdate;
	}
	public void setCreSdate(String creSdate) {
		this.creSdate = CommonUtil.convertDate(creSdate);
	}
	public String getCreEdate() {
		return creEdate;
	}
	public void setCreEdate(String creEdate) {
		this.creEdate = CommonUtil.convertDate(creEdate);
	}
	public String getParamListTypeCateg() {
		return paramListTypeCateg;
	}
	public void setParamListTypeCateg(String paramListTypeCateg) {
		this.paramListTypeCateg = paramListTypeCateg;
	}

	
	public String getTableMId() {
		return tableMId;
	}
	public void setTableMId(String tableMId) {
		this.tableMId = tableMId;
	}
	public String getFilterTable() {
		return filterTable;
	}
	public void setFilterTable(String filterTable) {
		this.filterTable = filterTable;
	}
	public String getFilterTableDesc() {
		return filterTableDesc;
	}
	public void setFilterTableDesc(String filterTableDesc) {
		this.filterTableDesc = filterTableDesc;
	}
	public String getFilterDescription() {
		return filterDescription;
	}
	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}

}
