package dream.part.rec.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.rec.dto.LovPoItemAcListDTO;

/**
 * Contents Lov Form
 * @author  nhkim8548
 * @version $Id: LovPoItemAcListForm.java,v 1.0 2018/08/06 09:26:40 nhkim8548 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPoItemAcListForm"
 */
public class LovPoItemAcListForm extends MaFinderAcForm
{
    private LovPoItemAcListDTO lovPoItemAcListDTO  = new LovPoItemAcListDTO();

    public LovPoItemAcListDTO getLovPoItemAcListDTO()
    {
        return lovPoItemAcListDTO;
    }

    public void setLovPoItemAcListDTO(LovPoItemAcListDTO lovPoItemAcListDTO)
    {
        this.lovPoItemAcListDTO = lovPoItemAcListDTO;
    }
}
