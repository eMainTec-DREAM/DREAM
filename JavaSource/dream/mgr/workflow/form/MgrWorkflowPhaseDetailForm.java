package dream.mgr.workflow.form;

import common.struts.BaseForm;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;

/**
 * Wokrflow Phase Page - Detail Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="mgrWorkflowPhaseDetailForm"
 */
public class MgrWorkflowPhaseDetailForm extends BaseForm
{
    private MgrWorkflowCommonDTO mgrWorkflowCommonDTO = new MgrWorkflowCommonDTO();
	private MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO = new MgrWorkflowPhaseListDTO();
	private MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO = new MgrWorkflowPhaseDetailDTO();
	
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
	public MgrWorkflowPhaseDetailDTO getMgrWorkflowPhaseDetailDTO() {
		return mgrWorkflowPhaseDetailDTO;
	}
	public void setMgrWorkflowPhaseDetailDTO(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO) {
		this.mgrWorkflowPhaseDetailDTO = mgrWorkflowPhaseDetailDTO;
	}
}
