package dream.consult.program.table.dto;

import common.bean.BaseDTO;

/**
 * �׸��� DTO
 * @author  kim21017
 * @version $Id: MaTableDTOList.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaTableDTOList extends BaseDTO
{
	/** ������ ���̺�������ID */
	private String tableMId 					= "";
	/** �������� */
	private String isDelCheck					= "";

	public String getIsDelCheck() {
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) {
		this.isDelCheck = isDelCheck;
	}
	public String getTableMId() {
		return tableMId;
	}
	public void setTableMId(String tableMId) {
		this.tableMId = tableMId;
	}
}
