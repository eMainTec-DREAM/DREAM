package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;

/**
 * ǥ�������׸� Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovStdCheckPointListForm"
 */
public class LovStdCheckPointListForm extends MaFinderAcForm
{
    /** ǥ�������׸� DTO */
    private LovStdCheckPointListDTO lovStdCheckPointListDTO = new LovStdCheckPointListDTO();

    public LovStdCheckPointListDTO getLovStdCheckPointListDTO()
    {
        return lovStdCheckPointListDTO;
    }

    public void setLovStdCheckPointListDTO(LovStdCheckPointListDTO lovStdCheckPointListDTO)
    {
        this.lovStdCheckPointListDTO = lovStdCheckPointListDTO;
    }

}
