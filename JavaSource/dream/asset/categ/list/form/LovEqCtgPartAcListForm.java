package dream.asset.categ.list.form;

import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 설비종류별부품 Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgPartAcListForm"
 */
public class LovEqCtgPartAcListForm extends MaFinderAcForm
{
    /** 설비종류별부품 DTO */
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
