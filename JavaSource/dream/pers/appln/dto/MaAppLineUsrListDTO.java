package dream.pers.appln.dto;

import common.bean.BaseDTO;

/**
 * DTO
 * @author  kim21017
 * @version $Id: MaAppLineUsrListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaAppLineUsrListDTO extends BaseDTO
{
    /** KEY ID */
    private String apprlineusrId    = "";
    
    
	/** ´äº¯ID */
	private String answerId 	= "";

	
	public String getApprlineusrId()
    {
        return apprlineusrId;
    }

    public void setApprlineusrId(String apprlineusrId)
    {
        this.apprlineusrId = apprlineusrId;
        super.setAuditKey(apprlineusrId);
    }

    public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
}