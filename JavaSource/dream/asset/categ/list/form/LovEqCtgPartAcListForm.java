package dream.asset.categ.list.form;

import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ������������ǰ Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgPartAcListForm"
 */
public class LovEqCtgPartAcListForm extends MaFinderAcForm
{
    /** ������������ǰ DTO */
    private LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO = new LovEqCtgPartAcListDTO();

    public LovEqCtgPartAcListDTO getLovEqCtgPartAcListDTO()
    {
        return lovEqCtgPartAcListDTO;
    }

    public void setLovEqCtgPartAcListDTO(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO)
    {
        this.lovEqCtgPartAcListDTO = lovEqCtgPartAcListDTO;
    }

}
