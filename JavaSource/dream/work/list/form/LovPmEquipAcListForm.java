package dream.work.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.list.dto.LovPmEquipAcListDTO;

/**
 * ����ǰ�� Lov Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPmEquipAcListForm"
 */
public class LovPmEquipAcListForm extends MaFinderAcForm
{
    /** ����ǰ�� Lov DTO */
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
