package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;

/**
 * �۾���ȹ���� - ��� form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workPlanApprListForm"
 */
public class WorkPlanApprListForm extends BaseForm
{    
    /** ���� */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();

	public WorkPlanApprCommonDTO getWorkPlanApprCommonDTO() {
		return workPlanApprCommonDTO;
	}

	public void setWorkPlanApprCommonDTO(WorkPlanApprCommonDTO workPlanApprCommonDTO) {
		this.workPlanApprCommonDTO = workPlanApprCommonDTO;
	}
    
}
