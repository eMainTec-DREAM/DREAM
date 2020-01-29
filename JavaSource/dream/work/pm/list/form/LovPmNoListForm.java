package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovPmNoListDTO;

/**
 * 예방점검팝업 Form
 * @author  kim21017
 * @version $Id: LovPmNoListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPmNoListForm"
 */
public class LovPmNoListForm extends MaFinderAcForm
{
    /** 예방점검팝업 DTO */
    private LovPmNoListDTO lovPmNoListDTO = new LovPmNoListDTO();

	public LovPmNoListDTO getLovPmNoListDTO() {
		return lovPmNoListDTO;
	}

	public void setLovPmNoListDTO(LovPmNoListDTO lovPmNoListDTO) {
		this.lovPmNoListDTO = lovPmNoListDTO;
	}
}
