package dream.rcm.funceq.form;

import common.struts.BaseForm;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;

/**
 * ���� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEquipCtgListForm"
 */
public class LovEquipCtgListForm extends BaseForm
{
    /** ���� DTO */
    private LovEquipCtgListDTO lovEquipCtgListDTO = new LovEquipCtgListDTO();

	public LovEquipCtgListDTO getLovEquipCtgListDTO() 
	{
		return lovEquipCtgListDTO;
	}

	public void setLovEquipCtgListDTO(LovEquipCtgListDTO lovEquipCtgListDTO) 
	{
		this.lovEquipCtgListDTO = lovEquipCtgListDTO;
	}
}
