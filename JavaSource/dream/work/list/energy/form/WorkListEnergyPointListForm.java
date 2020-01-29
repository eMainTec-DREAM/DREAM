package dream.work.list.energy.form;

import common.struts.BaseForm;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 목록 Form
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointListForm.java,v 1.0 2015/12/01 09:13:09 sy.yang Exp $
 * @since   1.0
 *
 * @struts.form name="workListEnergyPointListForm"
 */
public class WorkListEnergyPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = new WorkListEnergyMstrListCommonDTO();
    /** 에너지 값 측정항목 목록  */
    private WorkListEnergyPointListDTO workListEnergyPointListDTO = new WorkListEnergyPointListDTO();
	
    
    public WorkListEnergyMstrListCommonDTO getWorkListEnergyMstrListCommonDTO() {
		return workListEnergyMstrListCommonDTO;
	}
	public void setWorkListEnergyMstrListCommonDTO(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO) {
		this.workListEnergyMstrListCommonDTO = workListEnergyMstrListCommonDTO;
	}
	public WorkListEnergyPointListDTO getWorkListEnergyPointListDTO() {
		return workListEnergyPointListDTO;
	}
	public void setWorkListEnergyPointListDTO(WorkListEnergyPointListDTO workListEnergyPointListDTO) {
		this.workListEnergyPointListDTO = workListEnergyPointListDTO;
	}
    
}
