package dream.consult.comp.plant.form;

import common.struts.BaseForm;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;

/**
 * ȸ�缳�� - ��� form
 * @author  kim21017
 * @version $Id: MaPlantMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPlantMngListForm"
 */
public class MaPlantMngListForm extends BaseForm
{
    //===============================================================
    /** ȸ�缳�� ���� */
    private MaPlantMngCommonDTO maPlantMngCommonDTO = new MaPlantMngCommonDTO();

	public MaPlantMngCommonDTO getMaPlantMngCommonDTO() {
		return maPlantMngCommonDTO;
	}

	public void setMaPlantMngCommonDTO(MaPlantMngCommonDTO maPlantMngCommonDTO) {
		this.maPlantMngCommonDTO = maPlantMngCommonDTO;
	}

}
