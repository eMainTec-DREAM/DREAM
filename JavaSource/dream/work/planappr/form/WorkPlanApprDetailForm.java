package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * 작업계획승인- 상세 Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workPlanApprDetailForm"
 */
public class WorkPlanApprDetailForm extends BaseForm
{
    /** 공통 */ 
	private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** 상세 */
    private WorkPlanApprDetailDTO workPlanApprDetailDTO = new WorkPlanApprDetailDTO();
    /** 결재 진행 이력 DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
	public AppReqCommonDTO getAppReqCommonDTO() {
		return appReqCommonDTO;
	}
	public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO) {
		this.appReqCommonDTO = appReqCommonDTO;
	}
	public WorkPlanApprCommonDTO getWorkPlanApprCommonDTO() {
		return workPlanApprCommonDTO;
	}
	public void setWorkPlanApprCommonDTO(WorkPlanApprCommonDTO workPlanApprCommonDTO) {
		this.workPlanApprCommonDTO = workPlanApprCommonDTO;
	}
	public WorkPlanApprDetailDTO getWorkPlanApprDetailDTO() {
		return workPlanApprDetailDTO;
	}
	public void setWorkPlanApprDetailDTO(WorkPlanApprDetailDTO workPlanApprDetailDTO) {
		this.workPlanApprDetailDTO = workPlanApprDetailDTO;
	}
    
}
