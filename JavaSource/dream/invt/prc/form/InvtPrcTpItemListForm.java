package dream.invt.prc.form;

import common.struts.BaseForm;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * 구매절차Item 목록
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListForm.java,v 1.0 2015/12/01 09:13:09 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcTpItemListForm"
 */
public class InvtPrcTpItemListForm extends BaseForm
{    
    //===============================================================
    /** 구매절차 공통 */
    private InvtPrcTpCommonDTO invtPrcTpCommonDTO = new InvtPrcTpCommonDTO();
    /** 구매절차  */
    private InvtPrcTpItemListDTO invtPrcTpItemListDTO = new InvtPrcTpItemListDTO();
    
	public InvtPrcTpCommonDTO getInvtPrcTpCommonDTO() {
		return invtPrcTpCommonDTO;
	}

	public void setInvtPrcTpCommonDTO(InvtPrcTpCommonDTO invtPrcTpCommonDTO) {
		this.invtPrcTpCommonDTO = invtPrcTpCommonDTO;
	}

	public InvtPrcTpItemListDTO getInvtPrcTpItemListDTO() {
		return invtPrcTpItemListDTO;
	}

	public void setInvtPrcTpItemListDTO(InvtPrcTpItemListDTO invtPrcTpItemListDTO) {
		this.invtPrcTpItemListDTO = invtPrcTpItemListDTO;
	}
}
