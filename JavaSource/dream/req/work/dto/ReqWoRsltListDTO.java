package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * �۾���� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ReqWoRsltListDTO extends BaseDTO
{
    /** �۾���û ���䳻�� ID */
    private String woReqResId       = "";

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
    
}