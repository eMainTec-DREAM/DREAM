package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;

/**
 * 작업계획승인 - 목록 form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workPlanApprListForm"
 */
public class WorkPlanApprListForm extends BaseForm
{    
    /** 공통 */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();

	public WorkPlanApprCommonDTO getWorkPlanApprCommonDTO() {
		return workPlanApprCommonDTO;
	}

	public void setWorkPlanApprCommonDTO(WorkPlanApprCommonDTO workPlanApprCommonDTO) {
		this.workPlanApprCommonDTO = workPlanApprCommonDTO;
	}
    
}
