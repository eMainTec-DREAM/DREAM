package dream.consult.program.btn.form;

import common.struts.BaseForm;
import dream.consult.program.btn.dto.LovButtonListDTO;

/**
 * ��ư Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovButtonListForm"
 */
public class LovButtonListForm extends BaseForm
{
    /** ��ư DTO */
    private LovButtonListDTO lovButtonListDTO = new LovButtonListDTO();

	public LovButtonListDTO getLovButtonListDTO() 
	{
		return lovButtonListDTO;
	}

	public void setLovButtonListDTO(LovButtonListDTO lovButtonListDTO) 
	{
		this.lovButtonListDTO = lovButtonListDTO;
	}
}
