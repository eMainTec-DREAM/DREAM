package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * 휴무요일 설정 - 상세 DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalDowsetListDTO extends BaseDTO
{
	/** 휴무요일ID */
	private String wrkcalDowsetId 		= "";
	
	public String getWrkcalDowsetId() {
		return wrkcalDowsetId;
	}
	public void setWrkcalDowsetId(String wrkcalDowsetId) {
		this.wrkcalDowsetId = wrkcalDowsetId;
	}
	
}
