package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.LovStdPointListDTO;

/**
 * 표준항목선택 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovStdPointListForm"
 */
public class LovStdPointListForm extends BaseForm
{
    /** 거래처팝업 DTO */
    private LovStdPointListDTO lovStdPointListDTO = new LovStdPointListDTO();

	public LovStdPointListDTO getLovStdPointListDTO() 
	{
		return lovStdPointListDTO;
	}

	public void setLovStdPointListDTO(LovStdPointListDTO lovStdPointListDTO) 
	{
		this.lovStdPointListDTO = lovStdPointListDTO;
	}
}
