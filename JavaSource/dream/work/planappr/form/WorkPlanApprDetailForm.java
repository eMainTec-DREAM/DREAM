package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * �۾���ȹ����- �� Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workPlanApprDetailForm"
 */
public class WorkPlanApprDetailForm extends BaseForm
{
    /** ���� */ 
	private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** �� */
    private WorkPlanApprDetailDTO workPlanApprDetailDTO = new WorkPlanApprDetailDTO();
    /** ���� ���� �̷� DTO  */
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
