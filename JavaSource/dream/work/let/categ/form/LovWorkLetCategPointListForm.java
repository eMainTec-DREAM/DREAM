package dream.work.let.categ.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;

/**
 * �����۾��㰡�� ǥ�������׸� Lov Form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkLetCategPointListForm"
 */
public class LovWorkLetCategPointListForm extends MaFinderAcForm
{
    /** �����۾��㰡�� ǥ�������׸� Lov DTO */
    private LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO = new LovWorkLetCategPointListDTO();

    
	public LovWorkLetCategPointListDTO getLovWorkLetCategPointListDTO() {
		return lovWorkLetCategPointListDTO;
	}

	public void setLovWorkLetCategPointListDTO(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO) {
		this.lovWorkLetCategPointListDTO = lovWorkLetCategPointListDTO;
	}
    
}
