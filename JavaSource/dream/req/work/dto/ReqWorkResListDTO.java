package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * ó������ DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqWorkResListDTO extends BaseDTO
{
    /** ó������ ID */
    private String woReqResId       = "";
    
    /** ���ڸ�� ID */
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
