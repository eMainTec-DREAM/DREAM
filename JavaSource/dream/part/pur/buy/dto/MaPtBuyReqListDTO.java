package dream.part.pur.buy.dto;

import common.bean.BaseDTO;

/**
 * 备概脚没 何前   DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtBuyReqListDTO extends BaseDTO
{
	/** 磊犁夸没Item ID */
	private String ptPrItemId 	= "";
	/** 泅厘备概没备 ID */
	private String ptPnListId 	= "";
	
	public String getPtPnListId()
    {
        return ptPnListId;
    }

    public void setPtPnListId(String ptPnListId)
    {
        this.ptPnListId = ptPnListId;
    }

    public String getPtPrItemId() {
		return ptPrItemId;
	}

	public void setPtPrItemId(String ptPrItemId) {
		this.ptPrItemId = ptPrItemId;
		super.setAuditKey(ptPrItemId);
	}
	
}