package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * ���ڰ�� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ReqWoInvtRsltListDTO extends BaseDTO
{
    /** �۾���û ���䳻�� ID */
    private String woReqResId       = "";

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
		super.setAuditKey(woReqResId);
	}
    
}
