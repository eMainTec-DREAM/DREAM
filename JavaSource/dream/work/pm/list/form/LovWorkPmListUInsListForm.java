package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;

/**
 * 에너지 측정기준주기 Lov Form
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkPmListUInsListForm"
 */
public class LovWorkPmListUInsListForm extends MaFinderAcForm
{
    /** 설비종류별부품 DTO */
    private LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO = new LovWorkPmListUInsListDTO();

    public LovWorkPmListUInsListDTO getLovWorkPmListUInsListDTO()
    {
        return lovWorkPmListUInsListDTO;
    }

    public void setLovWorkPmListUInsListDTO(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO)
    {
        this.lovWorkPmListUInsListDTO = lovWorkPmListUInsListDTO;
    }

}
