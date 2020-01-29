package dream.consult.program.menu.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.menu.dto.LovMenuListDTO;

/**
 * 메뉴 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMenuListForm"
 */
public class LovMenuListForm extends MaFinderAcForm
{
    /** 메뉴 DTO */
    private LovMenuListDTO lovMenuListDTO = new LovMenuListDTO();

	public LovMenuListDTO getLovMenuListDTO() 
	{
		return lovMenuListDTO;
	}

	public void setLovMenuListDTO(LovMenuListDTO lovMenuListDTO) 
	{
		this.lovMenuListDTO = lovMenuListDTO;
	}
}
