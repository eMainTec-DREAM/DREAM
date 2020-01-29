package dream.consult.comp.plant.form;

import common.struts.BaseForm;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;

/**
 * 회사설정- 상세 Form
 * @author  kim21017
 * @version $Id: MaPlantMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPlantMngDetailForm"
 */
public class MaPlantMngDetailForm extends BaseForm
{
    //========================================================================
    /** 회사설정 공통 */
    private MaPlantMngCommonDTO maPlantMngCommonDTO = new MaPlantMngCommonDTO();
    //========================================================================
    /** 회사설정 상세 */
    private MaPlantMngDetailDTO maPlantMngDetailDTO = new MaPlantMngDetailDTO();


	public MaPlantMngCommonDTO getMaPlantMngCommonDTO() {
		return maPlantMngCommonDTO;
	}

	public void setMaPlantMngCommonDTO(MaPlantMngCommonDTO maPlantMngCommonDTO) {
		this.maPlantMngCommonDTO = maPlantMngCommonDTO;
	}

	public MaPlantMngDetailDTO getMaPlantMngDetailDTO() {
		return maPlantMngDetailDTO;
	}

	public void setMaPlantMngDetailDTO(MaPlantMngDetailDTO maPlantMngDetailDTO) {
		this.maPlantMngDetailDTO = maPlantMngDetailDTO;
	}

}
