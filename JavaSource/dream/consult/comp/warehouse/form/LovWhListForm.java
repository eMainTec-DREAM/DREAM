package dream.consult.comp.warehouse.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.warehouse.dto.LovWhListDTO;

/**
 * »ç¿ëÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovWhListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovWhListForm"
 */
public class LovWhListForm extends MaFinderAcForm
{
    /** »ç¿ëÆË¾÷ DTO */
    private LovWhListDTO lovWhListDTO = new LovWhListDTO();

	public LovWhListDTO getLovWhListDTO() {
		return lovWhListDTO;
	}

	public void setLovWhListDTO(LovWhListDTO lovWhListDTO) {
		this.lovWhListDTO = lovWhListDTO;
	}
}
