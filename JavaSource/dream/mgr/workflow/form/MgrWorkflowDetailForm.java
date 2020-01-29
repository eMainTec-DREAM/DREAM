package dream.mgr.workflow.form;

import common.struts.BaseForm;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;

/**
 * Wokrflow Page - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrWorkflowDetailForm"
 */
public class MgrWorkflowDetailForm extends BaseForm
{
	private MgrWorkflowCommonDTO mgrWorkflowCommonDTO = new MgrWorkflowCommonDTO();
	private MgrWorkflowDetailDTO mgrWorkflowDetailDTO = new MgrWorkflowDetailDTO();
    
	public MgrWorkflowCommonDTO getMgrWorkflowCommonDTO() {
		return mgrWorkflowCommonDTO;
	}
	public void setMgrWorkflowCommonDTO(MgrWorkflowCommonDTO mgrWorkflowCommonDTO) {
		this.mgrWorkflowCommonDTO = mgrWorkflowCommonDTO;
	}
	public MgrWorkflowDetailDTO getMgrWorkflowDetailDTO() {
		return mgrWorkflowDetailDTO;
	}
	public void setMgrWorkflowDetailDTO(MgrWorkflowDetailDTO mgrWorkflowDetailDTO) {
		this.mgrWorkflowDetailDTO = mgrWorkflowDetailDTO;
	}
}
