package dream.asset.categ.list.form;

import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 설비종류별제원 Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgSpecAcListForm"
 */
public class LovEqCtgSpecAcListForm extends MaFinderAcForm
{
    /** 설비종류별제원 DTO */
    private LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO = new LovEqCtgSpecAcListDTO();

    public LovEqCtgSpecAcListDTO getLovEqCtgSpecAcListDTO()
    {
        return lovEqCtgSpecAcListDTO;
    }

    public void setLovEqCtgSpecAcListDTO(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO)
    {
        this.lovEqCtgSpecAcListDTO = lovEqCtgSpecAcListDTO;
    }

}
