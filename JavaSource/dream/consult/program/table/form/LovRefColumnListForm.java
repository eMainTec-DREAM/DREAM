package dream.consult.program.table.form;

import common.struts.BaseForm;
import dream.consult.program.table.dto.LovRefColumnListDTO;

/**
 * Ref���̺� �˾�  Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovRefColumnListForm"
 */
public class LovRefColumnListForm extends BaseForm
{
    /** Ref���̺� �˾�DTO */
    private LovRefColumnListDTO lovRefColumnListDTO = new LovRefColumnListDTO();

	public LovRefColumnListDTO getLovRefColumnListDTO() 
	{
		return lovRefColumnListDTO;
	}

	public void setLovRefColumnListDTO(LovRefColumnListDTO lovRefColumnListDTO) 
	{
		this.lovRefColumnListDTO = lovRefColumnListDTO;
	}
}
