package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyActListDTO extends BaseDTO
{
    /** �����۾� Main Act ID */
    private String woDayActId       = "";

	public String getWoDayActId() {
		return woDayActId;
	}

	public void setWoDayActId(String woDayActId) {
		this.woDayActId = woDayActId;
	}
}
