package dream.consult.program.table.dto;

import common.bean.BaseDTO;

/**
 * 데이터 테이블 - 분류  DTO
 * @author  kim21017
 * @version $Id: MaTableColListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaTableColListDTO extends BaseDTO
{
	/** 데이터 테이블유형상세ID */
	private String tableDId 	= "";

	public String getTableDId() {
		return tableDId;
	}

	public void setTableDId(String tableDId) {
		this.tableDId = tableDId;
	}
}