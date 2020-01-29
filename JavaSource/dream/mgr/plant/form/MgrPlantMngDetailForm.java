package dream.mgr.plant.form;

import common.struts.BaseForm;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;

/**
 * ���弳��- �� Form
 * @author  euna0207
 * @version $Id: MgrPlantMngDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrPlantMngDetailForm"
 */
public class MgrPlantMngDetailForm extends BaseForm
{
    //========================================================================
    /** ���弳�� ���� */
    private MgrPlantMngCommonDTO mgrPlantMngCommonDTO = new MgrPlantMngCommonDTO();
    //========================================================================
    /** ���弳�� �� */
    private MgrPlantMngDetailDTO mgrPlantMngDetailDTO = new MgrPlantMngDetailDTO();


	public MgrPlantMngCommonDTO getMgrPlantMngCommonDTO() {
		return mgrPlantMngCommonDTO;
	}

	public void setMgrPlantMngCommonDTO(MgrPlantMngCommonDTO mgrPlantMngCommonDTO) {
		this.mgrPlantMngCommonDTO = mgrPlantMngCommonDTO;
	}

	public MgrPlantMngDetailDTO getMgrPlantMngDetailDTO() {
		return mgrPlantMngDetailDTO;
	}

	public void setMgrPlantMngDetailDTO(MgrPlantMngDetailDTO mgrPlantMngDetailDTO) {
		this.mgrPlantMngDetailDTO = mgrPlantMngDetailDTO;
	}

}
