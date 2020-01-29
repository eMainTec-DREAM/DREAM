package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * 투자결과 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class ReqWoInvtRsltListDTO extends BaseDTO
{
    /** 작업요청 응답내용 ID */
    private String woReqResId       = "";

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
		super.setAuditKey(woReqResId);
	}
    
}
