package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.LovEquipListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 설비 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEquipListForm"
 */
public class LovEquipListForm extends MaFinderAcForm
{
    /** 설비 DTO */
    private LovEquipListDTO lovEquipListDTO = new LovEquipListDTO();

	public LovEquipListDTO getLovEquipListDTO() 
	{
		return lovEquipListDTO;
	}

	public void setLovEquipListDTO(LovEquipListDTO lovEquipListDTO) 
	{
		this.lovEquipListDTO = lovEquipListDTO;
	}
}
