package dream.rcm.funceq.form;

import common.struts.BaseForm;

import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * 설비종류작업부위 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovRcmEqCtgAsmbListForm"
 */
public class LovRcmEqCtgAsmbListForm extends BaseForm
{
    /** 설비종류작업부위 DTO */
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
