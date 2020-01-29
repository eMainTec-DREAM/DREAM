package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovLineListDTO;
import common.struts.BaseForm;

/**
 * 무정지라인팝업 Form
 * @author  kim21017
 * @version $Id: LovLineListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovLineListForm"
 */
public class LovLineListForm extends BaseForm
{
    /** 자산팝업 DTO */
    private LovLineListDTO lovLineListDTO = new LovLineListDTO();

	public LovLineListDTO getLovLineListDTO() {
		return lovLineListDTO;
	}

	public void setLovLineListDTO(LovLineListDTO lovLineListDTO) {
		this.lovLineListDTO = lovLineListDTO;
	}
}
