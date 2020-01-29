package common.mafinder.mamstr.form;

import common.mafinder.mamstr.dto.LovPageListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 페이지 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPageListForm"
 */
public class LovPageListForm extends MaFinderAcForm
{
    /** 페이지 DTO */
    private LovPageListDTO lovPageListDTO = new LovPageListDTO();

	public LovPageListDTO getLovPageListDTO() 
	{
		return lovPageListDTO;
	}

	public void setLovPageListDTO(LovPageListDTO lovPageListDTO) 
	{
		this.lovPageListDTO = lovPageListDTO;
	}
}
