package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * 수리기안품의서 Form
 * @author  ssong
 * @version $Id:  $
 * @since   1.0
 *
 * @struts.form name="lovPtAppListForm"
 */
public class LovPtAppListForm extends BaseForm
{
    /** 수리기안품의서 DTO */
    private LovPtAppListDTO lovPtAppListDTO = new LovPtAppListDTO();

	public LovPtAppListDTO getLovPtAppListDTO() 
	{
		return lovPtAppListDTO;
	}

	public void setLovPtAppListDTO(LovPtAppListDTO lovPtAppListDTO) 
	{
		this.lovPtAppListDTO = lovPtAppListDTO;
	}
}
