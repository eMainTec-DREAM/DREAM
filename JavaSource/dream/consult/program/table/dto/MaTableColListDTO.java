package dream.consult.program.table.dto;

import common.bean.BaseDTO;

/**
 * ������ ���̺� - �з�  DTO
 * @author  kim21017
 * @version $Id: MaTableColListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaTableColListDTO extends BaseDTO
{
	/** ������ ���̺�������ID */
	private String tableDId 	= "";

	public String getTableDId() {
		return tableDId;
	}

	public void setTableDId(String tableDId) {
		this.tableDId = tableDId;
	}
}