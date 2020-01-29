package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovProductAcListDTO;

/**
 * ������ǰ Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovProductAcListForm"
 */
public class LovProductAcListForm extends MaFinderAcForm
{
    /** ������ǰ DTO */
    private LovProductAcListDTO lovProductAcListDTO = new LovProductAcListDTO();

    public LovProductAcListDTO getLovProductAcListDTO()
    {
        return lovProductAcListDTO;
    }

    public void setLovProductAcListDTO(LovProductAcListDTO lovProductAcListDTO)
    {
        this.lovProductAcListDTO = lovProductAcListDTO;
    }

}
