package dream.pers.priv.info.form;

import dream.comm.form.MaFinderAcForm;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;

/**
 * 공장권한 팝업 Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="lovUsrPlantAuthListForm"
 */
public class LovUsrPlantAuthListForm extends MaFinderAcForm
{
    /**  DTO */
    private LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO = new LovUsrPlantAuthListDTO();

	public LovUsrPlantAuthListDTO getLovUsrPlantAuthListDTO() {
		return lovUsrPlantAuthListDTO;
	}

	public void setLovUsrPlantAuthListDTO(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO) {
		this.lovUsrPlantAuthListDTO = lovUsrPlantAuthListDTO;
	}
}
