package dream.consult.program.table.dto;

import common.bean.BaseDTO;
/**
 * 테이블 LOV DTO
 * @author kim21017
 * @version $Id: TableValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class TableValLovDTO extends BaseDTO
{
	/**   table ID */
	private String tableId				= "";
	/**   table 명 */
	private String filterTableName		= "";
	/**   table 설명 */
	private String filterTableDesc		= "";
	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getFilterTableName() {
		return filterTableName;
	}
	public void setFilterTableName(String filterTableName) {
		this.filterTableName = filterTableName;
	}
	public String getFilterTableDesc() {
		return filterTableDesc;
	}
	public void setFilterTableDesc(String filterTableDesc) {
		this.filterTableDesc = filterTableDesc;
	}
	
}
