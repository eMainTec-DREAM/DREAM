package dream.consult.program.table.dto;

import common.bean.BaseDTO;

/**
 * 그리드 DTO
 * @author  kim21017
 * @version $Id: ListTypeCodeDTOList.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class ListTypeCodeDTOList extends BaseDTO
{
	/** 시스템유형분류ID */
	private String tableMId 					= "";
	/** 데이터 테이블유형상세ID */
	private String tabColId 					= "";
	/** 삭제여부 */
	private String isDelCheck					= "";
	
	public String getCdSysMId() {
		return tableMId;
	}
	public void setTableMId(String tableMId) {
		this.tableMId = tableMId;
	}
	public String getIsDelCheck() {
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) {
		this.isDelCheck = isDelCheck;
	}
	public String getTableDId() {
		return tabColId;
	}
	public void setTableDId(String tabColId) {
		this.tabColId = tabColId;
	}
}
