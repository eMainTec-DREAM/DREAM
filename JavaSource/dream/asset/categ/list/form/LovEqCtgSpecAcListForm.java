package dream.asset.categ.list.form;

import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * �������������� Form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgSpecAcListForm"
 */
public class LovEqCtgSpecAcListForm extends MaFinderAcForm
{
    /** �������������� DTO */
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
