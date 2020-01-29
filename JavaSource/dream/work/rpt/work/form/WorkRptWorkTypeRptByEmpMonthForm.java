package dream.work.rpt.work.form;

import common.struts.BaseForm;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * 담당자별작업현황 - 담당자 월별작업현황 탭 목록 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWorkTypeRptByEmpMonthForm"
 */
public class WorkRptWorkTypeRptByEmpMonthForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO = new WorkRptWorkTypeRptByEmpCommonDTO();
    
    private WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO = new WorkRptWorkTypeRptByEmpMonthDTO();

	public WorkRptWorkTypeRptByEmpCommonDTO getWorkRptWorkTypeRptByEmpCommonDTO() {
		return workRptWorkTypeRptByEmpCommonDTO;
	}

	public void setWorkRptWorkTypeRptByEmpCommonDTO(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO) {
		this.workRptWorkTypeRptByEmpCommonDTO = workRptWorkTypeRptByEmpCommonDTO;
	}

	public WorkRptWorkTypeRptByEmpMonthDTO getWorkRptWorkTypeRptByEmpMonthDTO() {
		return workRptWorkTypeRptByEmpMonthDTO;
	}

	public void setWorkRptWorkTypeRptByEmpMonthDTO(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO) {
		this.workRptWorkTypeRptByEmpMonthDTO = workRptWorkTypeRptByEmpMonthDTO;
	}
    
}
