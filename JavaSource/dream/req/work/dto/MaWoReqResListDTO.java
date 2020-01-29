package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * 贸府荤亲 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoReqResListDTO extends BaseDTO
{
    /** 贸府荤亲 ID */
    private String woReqResId       = "";

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
		super.setAuditKey(woReqResId);
	}
    
}
