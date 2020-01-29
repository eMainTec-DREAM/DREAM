package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;

/**
 * 표준점검항목 Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovStdCheckPointListForm"
 */
public class LovStdCheckPointListForm extends MaFinderAcForm
{
    /** 표준점검항목 DTO */
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
