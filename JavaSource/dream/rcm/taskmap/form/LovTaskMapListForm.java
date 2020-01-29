package dream.rcm.taskmap.form;

import dream.comm.form.MaFinderAcForm;
import dream.rcm.taskmap.dto.LovTaskMapListDTO;

/**
 * ÁúÀÇÆË¾÷ Form
 * @author  hyosung
 * @version $Id: LovTaskMapListForm.java,v 1.0 2016/01/18 09:12:46 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="lovTaskMapListForm"
 */
public class LovTaskMapListForm extends MaFinderAcForm
{
    /** ÁúÀÇÆË¾÷ DTO */
    private LovTaskMapListDTO lovTaskMapListDTO = new LovTaskMapListDTO();

    public LovTaskMapListDTO getLovTaskMapListDTO() {
        return lovTaskMapListDTO;
    }

    public void setLovTaskMapListDTO(LovTaskMapListDTO lovTaskMapListDTO) {
        this.lovTaskMapListDTO = lovTaskMapListDTO;
    }
}
