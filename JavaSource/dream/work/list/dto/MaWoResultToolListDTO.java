package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업결과 투입공기구 DTO
 * @author  kim21017
 * @version $Id: MaWoResultToolListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultToolListDTO extends BaseDTO
{
	/** 작업결과 투입자재 id */
	private String woToolId 	= "";
	
    public String getWoToolId() {
		return woToolId;
	}

    public void setWoToolId(String woToolId) {
		this.woToolId = woToolId;
	}

}