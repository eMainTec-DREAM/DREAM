package dream.part.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.list.dto.LovPartsListDTO;

/**
 * 濠營で機 Form
 * @author  kim21017
 * @version $Id: LovPartsListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPartsListForm"
 */
public class LovPartsListForm extends MaFinderAcForm
{
    /** 濠營で機 DTO */
    private LovPartsListDTO lovPartsListDTO = new LovPartsListDTO();

	public LovPartsListDTO getLovPartsListDTO() {
		return lovPartsListDTO;
	}

	public void setLovPartsListDTO(LovPartsListDTO lovPartsListDTO) {
		this.lovPartsListDTO = lovPartsListDTO;
	}
}
