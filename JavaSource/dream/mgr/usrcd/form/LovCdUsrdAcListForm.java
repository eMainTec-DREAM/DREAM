package dream.mgr.usrcd.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;

/**
 * ���ڵ� Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovCdUsrdAcListForm"
 */
public class LovCdUsrdAcListForm extends MaFinderAcForm
{
    /** ���ڵ� DTO */
    private LovCdUsrdAcListDTO lovCdUsrdAcListDTO = new LovCdUsrdAcListDTO();

    public LovCdUsrdAcListDTO getLovCdUsrdAcListDTO()
    {
        return lovCdUsrdAcListDTO;
    }

    public void setLovCdUsrdAcListDTO(LovCdUsrdAcListDTO lovCdUsrdAcListDTO)
    {
        this.lovCdUsrdAcListDTO = lovCdUsrdAcListDTO;
    }

}
