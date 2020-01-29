package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyActListDTO extends BaseDTO
{
    /** 일일작업 Main Act ID */
    private String woDayActId       = "";

	public String getWoDayActId() {
		return woDayActId;
	}

	public void setWoDayActId(String woDayActId) {
		this.woDayActId = woDayActId;
	}
}
