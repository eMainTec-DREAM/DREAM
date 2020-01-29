package dream.work.let.categ.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;

/**
 * 안전작업허가서 표준점검항목 Lov Form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovWorkLetCategPointListForm"
 */
public class LovWorkLetCategPointListForm extends MaFinderAcForm
{
    /** 안전작업허가서 표준점검항목 Lov DTO */
    private LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO = new LovWorkLetCategPointListDTO();

    
	public LovWorkLetCategPointListDTO getLovWorkLetCategPointListDTO() {
		return lovWorkLetCategPointListDTO;
	}

	public void setLovWorkLetCategPointListDTO(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO) {
		this.lovWorkLetCategPointListDTO = lovWorkLetCategPointListDTO;
	}
    
}
