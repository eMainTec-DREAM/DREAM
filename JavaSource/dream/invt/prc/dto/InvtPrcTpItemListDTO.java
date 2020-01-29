package dream.invt.prc.dto;

import common.bean.BaseDTO;

/**
 * 구매절차 Item DTO
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListDTO.java,v 1.1 2015/12/04 09:10:45 hyosung Exp $
 * @since   1.0
 */
public class InvtPrcTpItemListDTO extends BaseDTO
{

	/** 구매절차 단계 ID*/
	private String invtPrcPhId     = "";
	
	public String getInvtPrcPhId()
    {
        return invtPrcPhId;
    }

    public void setInvtPrcPhId(String invtPrcPhId)
    {
        this.invtPrcPhId = invtPrcPhId;
        super.setAuditKey(invtPrcPhId);
    }

	
	

}