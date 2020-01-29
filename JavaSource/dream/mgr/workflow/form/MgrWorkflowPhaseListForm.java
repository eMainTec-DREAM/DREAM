package dream.mgr.workflow.form;

import common.struts.BaseForm;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
/**
 * Wokrflow Phase Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrWorkflowPhaseListForm"
 * */

public class MgrWorkflowPhaseListForm extends BaseForm{
	
    private MgrWorkflowCommonDTO mgrWorkflowCommonDTO = new MgrWorkflowCommonDTO();
	private MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO = new MgrWorkflowPhaseListDTO();
	
	public MgrWorkflowCommonDTO getMgrWorkflowCommonDTO()
    {
        return mgrWorkflowCommonDTO;
    }

    public void setMgrWorkflowCommonDTO(MgrWorkflowCommonDTO mgrWorkflowCommonDTO)
    {
        this.mgrWorkflowCommonDTO = mgrWorkflowCommonDTO;
    }

    public MgrWorkflowPhaseListDTO getMgrWorkflowPhaseListDTO() {
		return mgrWorkflowPhaseListDTO;
	}

	public void setMgrWorkflowPhaseListDTO(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO) {
		this.mgrWorkflowPhaseListDTO = mgrWorkflowPhaseListDTO;
	}
	
}
