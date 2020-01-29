package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.dto.WorkPlanApprWorkListDTO;

/**
 * 작업계획승인 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workPlanApprWorkListForm"
 */
public class WorkPlanApprWorkListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** 상세 */
    private WorkPlanApprDetailDTO workPlanApprDetailDTO = new WorkPlanApprDetailDTO();
    /** 작업계획 */
    private WorkPlanApprWorkListDTO workPlanApprWorkListDTO = new WorkPlanApprWorkListDTO();
    
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}
	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
	public WorkPlanApprDetailDTO getWorkPlanApprDetailDTO() {
		return workPlanApprDetailDTO;
	}
	public void setWorkPlanApprDetailDTO(WorkPlanApprDetailDTO workPlanApprDetailDTO) {
		this.workPlanApprDetailDTO = workPlanApprDetailDTO;
	}
	public WorkPlanApprCommonDTO getWorkPlanApprCommonDTO() {
		return workPlanApprCommonDTO;
	}
	public void setWorkPlanApprCommonDTO(WorkPlanApprCommonDTO workPlanApprCommonDTO) {
		this.workPlanApprCommonDTO = workPlanApprCommonDTO;
	}
	public WorkPlanApprWorkListDTO getWorkPlanApprWorkListDTO() {
		return workPlanApprWorkListDTO;
	}
	public void setWorkPlanApprWorkListDTO(WorkPlanApprWorkListDTO workPlanApprWorkListDTO) {
		this.workPlanApprWorkListDTO = workPlanApprWorkListDTO;
	}
	
}
