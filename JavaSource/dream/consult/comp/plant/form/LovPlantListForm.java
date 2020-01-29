package dream.consult.comp.plant.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.plant.dto.LovPlantListDTO;

/**
 * ÇÃ·£Æ® ÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovPlantListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPlantListForm"
 */
public class LovPlantListForm extends MaFinderAcForm
{
    /**  DTO */
    private LovPlantListDTO lovPlantListDTO = new LovPlantListDTO();

	public LovPlantListDTO getLovPlantListDTO() {
		return lovPlantListDTO;
	}

	public void setLovPlantListDTO(LovPlantListDTO lovPlantListDTO) {
		this.lovPlantListDTO = lovPlantListDTO;
	}
}
