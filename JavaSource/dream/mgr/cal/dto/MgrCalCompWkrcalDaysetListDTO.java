package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * �޹��� ���� - �� DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalDaysetListDTO extends BaseDTO
{
	/** �޹�����ID */
	private String wrkcalDaysetId 		= "";
	
	public String getWrkcalDaysetId() {
		return wrkcalDaysetId;
	}
	public void setWrkcalDaysetId(String wrkcalDaysetId) {
		this.wrkcalDaysetId = wrkcalDaysetId;
	}
	
}
