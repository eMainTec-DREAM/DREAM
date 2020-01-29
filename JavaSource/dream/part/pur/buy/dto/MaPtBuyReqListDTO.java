package dream.part.pur.buy.dto;

import common.bean.BaseDTO;

/**
 * ���Ž�û ��ǰ   DTO
 * @author  kim21017
 * @version $Id: MaPtBuyReqListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPtBuyReqListDTO extends BaseDTO
{
	/** �����ûItem ID */
	private String ptPrItemId 	= "";
	/** ���屸��û�� ID */
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