package dream.part.pur.buy.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.pur.buy.dto.LovPartPurBuyPnListDTO;

/**
 * 현장신청부품 선택 Lov Form
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPartPurBuyPnListForm"
 */
public class LovPartPurBuyPnListForm extends MaFinderAcForm
{
    /** 현장신청부품 선택 Lov DTO */
    private LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO = new LovPartPurBuyPnListDTO();

	public LovPartPurBuyPnListDTO getLovPartPurBuyPnListDTO() {
		return lovPartPurBuyPnListDTO;
	}

	public void setLovPartPurBuyPnListDTO(LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO) {
		this.lovPartPurBuyPnListDTO = lovPartPurBuyPnListDTO;
	}
}
