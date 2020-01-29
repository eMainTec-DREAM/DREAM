package dream.consult.comp.warehouse.form;

import common.struts.BaseForm;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;

/**
 * »ç¿ëÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovWhToolListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovWhToolListForm"
 */
public class LovWhToolListForm extends BaseForm
{
    /** »ç¿ëÆË¾÷ DTO */
    private LovWhToolListDTO lovWhToolListDTO = new LovWhToolListDTO();

	public LovWhToolListDTO getLovWhToolListDTO() {
		return lovWhToolListDTO;
	}

	public void setLovWhToolListDTO(LovWhToolListDTO lovWhToolListDTO) {
		this.lovWhToolListDTO = lovWhToolListDTO;
	}
}
