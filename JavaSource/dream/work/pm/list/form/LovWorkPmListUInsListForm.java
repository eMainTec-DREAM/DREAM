package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;

/**
 * ������ ���������ֱ� Lov Form
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkPmListUInsListForm"
 */
public class LovWorkPmListUInsListForm extends MaFinderAcForm
{
    /** ������������ǰ DTO */
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
