package dream.mgr.plant.form;

import common.struts.BaseForm;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;

/**
 * ���弳�� - ��� form
 * @author  euna0207
 * @version $Id: MgrPlantMngListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrPlantMngListForm"
 */
public class MgrPlantMngListForm extends BaseForm
{
    //===============================================================
    /** ���弳�� ���� */
    private MgrPlantMngCommonDTO mgrPlantMngCommonDTO = new MgrPlantMngCommonDTO();

	public MgrPlantMngCommonDTO getMgrPlantMngCommonDTO() {
		return mgrPlantMngCommonDTO;
	}

	public void setMgrPlantMngCommonDTO(MgrPlantMngCommonDTO mgrPlantMngCommonDTO) {
		this.mgrPlantMngCommonDTO = mgrPlantMngCommonDTO;
	}

}
