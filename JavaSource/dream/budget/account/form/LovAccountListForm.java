package dream.budget.account.form;

import dream.budget.account.dto.LovAccountListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 예산계정LOV  Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovAccountListForm"
 */
public class LovAccountListForm extends MaFinderAcForm
{
    /** 예산계정 DTO */
    private LovAccountListDTO lovAccountListDTO = new LovAccountListDTO();

	public LovAccountListDTO getLovAccountListDTO() {
		return lovAccountListDTO;
	}

	public void setLovAccountListDTO(LovAccountListDTO lovAccountListDTO) {
		this.lovAccountListDTO = lovAccountListDTO;
	}
}
