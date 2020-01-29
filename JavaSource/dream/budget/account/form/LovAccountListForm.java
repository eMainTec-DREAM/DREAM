package dream.budget.account.form;

import dream.budget.account.dto.LovAccountListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * �������LOV  Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovAccountListForm"
 */
public class LovAccountListForm extends MaFinderAcForm
{
    /** ������� DTO */
    private LovAccountListDTO lovAccountListDTO = new LovAccountListDTO();

	public LovAccountListDTO getLovAccountListDTO() {
		return lovAccountListDTO;
	}

	public void setLovAccountListDTO(LovAccountListDTO lovAccountListDTO) {
		this.lovAccountListDTO = lovAccountListDTO;
	}
}
