package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;

/**
 * 점검항목 Form
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkPmDInsListForm"
 */
public class LovWorkPmDInsListForm extends MaFinderAcForm
{
    /** 설비종류별부품 DTO */
    private LovWorkPmDInsListDTO lovWorkPmDInsListDTO = new LovWorkPmDInsListDTO();

    public LovWorkPmDInsListDTO getLovWorkPmDInsListDTO()
    {
        return lovWorkPmDInsListDTO;
    }

    public void setLovWorkPmDInsListDTO(LovWorkPmDInsListDTO lovWorkPmDInsListDTO)
    {
        this.lovWorkPmDInsListDTO = lovWorkPmDInsListDTO;
    }

}
