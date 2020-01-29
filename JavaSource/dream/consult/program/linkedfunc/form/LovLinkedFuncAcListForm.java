package dream.consult.program.linkedfunc.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;

/**
 * Linked Function Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovLinkedFuncAcListForm"
 */
public class LovLinkedFuncAcListForm extends MaFinderAcForm
{
    /** ∆‰¿Ã¡ˆ DTO */
    private LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO = new LovLinkedFuncAcListDTO();

	public LovLinkedFuncAcListDTO getLovLinkedFuncAcListDTO() 
	{
		return lovLinkedFuncAcListDTO;
	}

	public void setLovLinkedFuncAcListDTO(LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO) 
	{
		this.lovLinkedFuncAcListDTO = lovLinkedFuncAcListDTO;
	}
}
