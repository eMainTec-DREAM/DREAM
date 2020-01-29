package dream.work.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.list.dto.LovPmEquipAcListDTO;

/**
 * 积魂前格 Lov Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPmEquipAcListForm"
 */
public class LovPmEquipAcListForm extends MaFinderAcForm
{
    /** 积魂前格 Lov DTO */
    private LovPmEquipAcListDTO lovPmEquipAcListDTO = new LovPmEquipAcListDTO();

    public LovPmEquipAcListDTO getLovPmEquipAcListDTO()
    {
        return lovPmEquipAcListDTO;
    }

    public void setLovPmEquipAcListDTO(LovPmEquipAcListDTO lovPmEquipAcListDTO)
    {
        this.lovPmEquipAcListDTO = lovPmEquipAcListDTO;
    }

}
