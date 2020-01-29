package dream.work.list.energy.form;

import common.struts.BaseForm;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 등록 목록 form
 * @author  sy.yang
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="workListEnergyMstrListForm"
 */
public class WorkListEnergyMstrListForm extends BaseForm
{    
    //===============================================================
    /** 에너지값 공통 */
    private WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = new WorkListEnergyMstrListCommonDTO();
    /** 에너지값 상세 */
    private WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = new WorkListEnergyMstrDetailDTO();
    
    
	public WorkListEnergyMstrListCommonDTO getWorkListEnergyMstrListCommonDTO() {
		return workListEnergyMstrListCommonDTO;
	}
	public void setWorkListEnergyMstrListCommonDTO(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO) {
		this.workListEnergyMstrListCommonDTO = workListEnergyMstrListCommonDTO;
	}
	public WorkListEnergyMstrDetailDTO getWorkListEnergyMstrDetailDTO() {
		return workListEnergyMstrDetailDTO;
	}
	public void setWorkListEnergyMstrDetailDTO(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO) {
		this.workListEnergyMstrDetailDTO = workListEnergyMstrDetailDTO;
	}
    
}
