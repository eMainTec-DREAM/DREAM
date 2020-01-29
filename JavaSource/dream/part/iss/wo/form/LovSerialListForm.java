package dream.part.iss.wo.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.iss.wo.dto.LovSerialListDTO;

/**
 * SerialÆË¾÷ Form
 * @author  hyosung
 * @version $Id: LovSerialListForm.java,v 1.0 2016/01/18 09:12:46 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="lovSerialListForm"
 */
public class LovSerialListForm extends MaFinderAcForm
{
    /** SerialÆË¾÷ DTO */
    private LovSerialListDTO lovSerialListDTO = new LovSerialListDTO();

    public LovSerialListDTO getLovSerialListDTO()
    {
        return lovSerialListDTO;
    }

    public void setLovSerialListDTO(LovSerialListDTO lovSerialListDTO)
    {
        this.lovSerialListDTO = lovSerialListDTO;
    }

    
    
    
}
