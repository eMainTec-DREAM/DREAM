package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovWoListDTO;
import common.struts.BaseForm;
import dream.comm.form.MaFinderAcForm;

/**
 * 작업 Form
 * @author  kim21017
 * @version $Id: LovWoListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovWoListForm"
 */
public class LovWoListForm extends MaFinderAcForm
{
    /** 사용팝업 DTO */
    private LovWoListDTO lovWoListDTO = new LovWoListDTO();

	public LovWoListDTO getLovWoListDTO() {
		return lovWoListDTO;
	}

	public void setLovWoListDTO(LovWoListDTO lovWoListDTO) {
		this.lovWoListDTO = lovWoListDTO;
	}
}
