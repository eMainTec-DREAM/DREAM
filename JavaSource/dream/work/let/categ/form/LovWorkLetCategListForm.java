package dream.work.let.categ.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;

/**
 * 안전작업유형 Lov Form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkLetCategListForm"
 */
public class LovWorkLetCategListForm extends MaFinderAcForm
{
    /** 안전작업유형 Lov DTO */
    private LovWorkLetCategListDTO lovWorkLetCategListDTO = new LovWorkLetCategListDTO();

    
    public LovWorkLetCategListDTO getLovWorkLetCategListDTO()
    {
        return lovWorkLetCategListDTO;
    }

    public void setLovWorkLetCategListDTO(LovWorkLetCategListDTO lovWorkLetCategListDTO)
    {
        this.lovWorkLetCategListDTO = lovWorkLetCategListDTO;
    }

}
