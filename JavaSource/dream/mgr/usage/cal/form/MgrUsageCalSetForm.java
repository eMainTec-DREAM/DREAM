package dream.mgr.usage.cal.form;

import common.struts.BaseForm;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;

/**
 * ���޷¼��� - ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="mgrUsageCalSetForm"
 */
public class MgrUsageCalSetForm extends BaseForm
{    
    //===============================================================
	/** �����ð����� ���� */ 
    private MgrUsageCalSetDTO mgrUsageCalSetDTO = new MgrUsageCalSetDTO();

	public MgrUsageCalSetDTO getMgrUsageCalSetDTO() {
		return mgrUsageCalSetDTO;
	}

	public void setMgrUsageCalSetDTO(MgrUsageCalSetDTO mgrUsageCalSetDTO) {
		this.mgrUsageCalSetDTO = mgrUsageCalSetDTO;
	}

}
