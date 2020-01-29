package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovEqAppListDTO;
import common.struts.BaseForm;

/**
 * 설비기안품의서 Form
 * @author  kim21017
 * @version $Id: LovEqAppListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovEqAppListForm"
 */
public class LovEqAppListForm extends BaseForm
{
    /** 설비기안품의서 DTO */
    private LovEqAppListDTO lovEqAppListDTO = new LovEqAppListDTO();

	public LovEqAppListDTO getLovEqAppListDTO() {
		return lovEqAppListDTO;
	}

	public void setLovEqAppListDTO(LovEqAppListDTO lovEqAppListDTO) {
		this.lovEqAppListDTO = lovEqAppListDTO;
	}
}
