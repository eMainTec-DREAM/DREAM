package dream.mgr.workflow.form;

import common.struts.BaseForm;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
/**
 * Wokrflow Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrWorkflowListForm"
 * */

public class MgrWorkflowListForm extends BaseForm{
	
	private MgrWorkflowCommonDTO mgrWorkflowCommonDTO = new MgrWorkflowCommonDTO();

	public MgrWorkflowCommonDTO getMgrWorkflowCommonDTO() {
		return mgrWorkflowCommonDTO;
	}

	public void setMgrWorkflowCommonDTO(MgrWorkflowCommonDTO mgrWorkflowCommonDTO) {
		this.mgrWorkflowCommonDTO = mgrWorkflowCommonDTO;
	}
	
}
