package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * �������ǰ�Ǽ� Form
 * @author  ssong
 * @version $Id:  $
 * @since   1.0
 *
 * @struts.form name="lovPtAppListForm"
 */
public class LovPtAppListForm extends BaseForm
{
    /** �������ǰ�Ǽ� DTO */
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
