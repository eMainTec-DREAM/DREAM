package dream.part.iss.emg.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;

/**
 * 긴급출고 자재선택  Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEmgPartListForm"
 */
public class LovEmgPartListForm extends MaFinderAcForm
{
    /**  DTO */
    private LovEmgPartListDTO lovEmgPartListDTO = new LovEmgPartListDTO();

	public LovEmgPartListDTO getLovEmgPartListDTO() 
	{
		return lovEmgPartListDTO;
	}

	public void setLovEmgPartListDTO(LovEmgPartListDTO lovEmgPartListDTO) 
	{
		this.lovEmgPartListDTO = lovEmgPartListDTO;
	}
}
