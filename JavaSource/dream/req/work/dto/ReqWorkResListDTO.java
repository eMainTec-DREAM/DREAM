package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * 처리사항 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqWorkResListDTO extends BaseDTO
{
    /** 처리사항 ID */
    private String woReqResId       = "";
    
    /** 투자목록 ID */
    private String invtlistId		= "";
    
	public String getInvtlistId() {
		return invtlistId;
	}

	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
    
    

}
