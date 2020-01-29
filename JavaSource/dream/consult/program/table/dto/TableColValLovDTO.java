package dream.consult.program.table.dto;

import common.bean.BaseDTO;
/**
 * 테이블 Column LOV DTO
 * @author kim21017
 * @version $Id: TableColValLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class TableColValLovDTO extends BaseDTO
{
	/**   tableCol ID */
	private String tabColId					= "";
	/**   tableCol 명 */
	private String filterColName			= "";
	/**   tableCol 설명 */
	private String filterColDesc			= "";
	
	public String getTabColId() {
		return tabColId;
	}
	public void setTabColId(String tabColId) {
		this.tabColId = tabColId;
	}
	public String getFilterColName() {
		return filterColName;
	}
	public void setFilterColName(String filterColName) {
		this.filterColName = filterColName;
	}
	public String getFilterColDesc() {
		return filterColDesc;
	}
	public void setFilterColDesc(String filterColDesc) {
		this.filterColDesc = filterColDesc;
	}
	
}
