package dream.work.let.categ.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;

/**
 * �����۾����� Lov Form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkLetCategListForm"
 */
public class LovWorkLetCategListForm extends MaFinderAcForm
{
    /** �����۾����� Lov DTO */
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
