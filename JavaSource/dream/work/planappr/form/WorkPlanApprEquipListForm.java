package dream.work.planappr.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.dto.WorkPlanApprEquipListDTO;

/**
 * 작업계획승인 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workPlanApprEquipListForm"
 */
public class WorkPlanApprEquipListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** 상세 */
    private WorkPlanApprDetailDTO workPlanApprDetailDTO = new WorkPlanApprDetailDTO();
    /** 작업계획 */
    private WorkPlanApprEquipListDTO workPlanApprEquipListDTO = new WorkPlanApprEquipListDTO();
    
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
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
	public WorkPlanApprEquipListDTO getWorkPlanApprEquipListDTO() {
		return workPlanApprEquipListDTO;
	}
	public void setWorkPlanApprEquipListDTO(WorkPlanApprEquipListDTO workPlanApprEquipListDTO) {
		this.workPlanApprEquipListDTO = workPlanApprEquipListDTO;
	}
	
}
