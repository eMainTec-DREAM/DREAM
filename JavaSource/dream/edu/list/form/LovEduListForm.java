package dream.edu.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.edu.list.dto.LovEduListDTO;

/**
 * ±³À°°úÁ¤ Form
 * @author  hyosung
 * @version $Id: LovEduListForm.java,v 1.0 2016/01/18 09:12:46 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="lovEduListForm"
 */
public class LovEduListForm extends MaFinderAcForm
{
    /** ÀÚ»êÆË¾÷ DTO */
    private LovEduListDTO lovEduListDTO = new LovEduListDTO();

	public LovEduListDTO getLovEduListDTO() {
		return lovEduListDTO;
	}

	public void setLovEduListDTO(LovEduListDTO lovEduListDTO) {
		this.lovEduListDTO = lovEduListDTO;
	}
}
