package dream.consult.program.lang.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.lang.dto.LovLangListDTO;

/**
 * ´Ù±¸¾îÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovLangListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovLangListForm"
 */
public class LovLangListForm extends MaFinderAcForm
{
    /** ´Ù±¹¾îÆË¾÷ DTO */
    private LovLangListDTO lovLangListDTO = new LovLangListDTO();

	public LovLangListDTO getLovLangListDTO() {
		return lovLangListDTO;
	}

	public void setLovLangListDTO(LovLangListDTO lovLangListDTO) {
		this.lovLangListDTO = lovLangListDTO;
	}
}
