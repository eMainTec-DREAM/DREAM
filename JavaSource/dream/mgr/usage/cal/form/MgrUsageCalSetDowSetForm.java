package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;

/**
 * ���Ϻ� ���� ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetDowSetForm"
 */
public class MgrUsageCalSetDowSetForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MgrUsageCalSetDTO mgrUsageCalSetDTO = new MgrUsageCalSetDTO();
    /** ���Ϻ� ���� ���  */
    private MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = new MgrUsageCalSetDowSetDTO();
	

	public MgrUsageCalSetDTO getMgrUsageCalSetDTO() {
		return mgrUsageCalSetDTO;
	}

	public void setMgrUsageCalSetDTO(MgrUsageCalSetDTO mgrUsageCalSetDTO) {
		this.mgrUsageCalSetDTO = mgrUsageCalSetDTO;
	}

	public MgrUsageCalSetDowSetDTO getMgrUsageCalSetDowSetDTO() {
		return mgrUsageCalSetDowSetDTO;
	}

	public void setMgrUsageCalSetDowSetDTO(MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO) {
		this.mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetDTO;
	}
}
