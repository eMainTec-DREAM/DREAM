package dream.rcm.funceq.form;

import common.struts.BaseForm;

import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * ���������۾����� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovRcmEqCtgAsmbListForm"
 */
public class LovRcmEqCtgAsmbListForm extends BaseForm
{
    /** ���������۾����� DTO */
    private LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO = new LovRcmEqCtgAsmbListDTO();

	public LovRcmEqCtgAsmbListDTO getLovRcmEqCtgAsmbListDTO() 
	{
		return lovRcmEqCtgAsmbListDTO;
	}

	public void setLovRcmEqCtgAsmbListDTO(LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO) 
	{
		this.lovRcmEqCtgAsmbListDTO = lovRcmEqCtgAsmbListDTO;
	}
}
