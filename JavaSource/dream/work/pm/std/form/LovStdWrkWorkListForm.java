package dream.work.pm.std.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;

/**
 * ǥ�������׸� Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovStdWrkWorkListForm"
 */
public class LovStdWrkWorkListForm extends MaFinderAcForm
{
    /** ǥ�������׸� DTO */
    private LovStdWrkWorkListDTO lovStdWrkWorkListDTO = new LovStdWrkWorkListDTO();

    public LovStdWrkWorkListDTO getLovStdWrkWorkListDTO()
    {
        return lovStdWrkWorkListDTO;
    }

    public void setLovStdWrkWorkListDTO(LovStdWrkWorkListDTO lovStdWrkWorkListDTO)
    {
        this.lovStdWrkWorkListDTO = lovStdWrkWorkListDTO;
    }

}
