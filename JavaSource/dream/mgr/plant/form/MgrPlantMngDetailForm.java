package dream.mgr.plant.form;

import common.struts.BaseForm;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;

/**
 * 공장설정- 상세 Form
 * @author  euna0207
 * @version $Id: MgrPlantMngDetailForm.java,v 1.0 2015/12/02 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="mgrPlantMngDetailForm"
 */
public class MgrPlantMngDetailForm extends BaseForm
{
    //========================================================================
    /** 공장설정 공통 */
    private MgrPlantMngCommonDTO mgrPlantMngCommonDTO = new MgrPlantMngCommonDTO();
    //========================================================================
    /** 공장설정 상세 */
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
