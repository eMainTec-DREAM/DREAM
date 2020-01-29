package dream.work.rpt.work.form;

import common.struts.BaseForm;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * 담당자별작업현황 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWorkTypeRptByEmpListForm"
 */
public class WorkRptWorkTypeRptByEmpListForm extends BaseForm
{    
    /** 공통 */
    private WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO = new WorkRptWorkTypeRptByEmpCommonDTO();

    /** 탭 - 월별현황 */
    private WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO = new WorkRptWorkTypeRptByEmpMonthDTO();
    /** 탭 - 작업종류별현황 */
    private WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO = new WorkRptWorkTypeRptByEmpWoTypeDTO();
    
    
	public WorkRptWorkTypeRptByEmpWoTypeDTO getWorkRptWorkTypeRptByEmpWoTypeDTO() {
		return workRptWorkTypeRptByEmpWoTypeDTO;
	}

	public void setWorkRptWorkTypeRptByEmpWoTypeDTO(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO) {
		this.workRptWorkTypeRptByEmpWoTypeDTO = workRptWorkTypeRptByEmpWoTypeDTO;
	}

	public WorkRptWorkTypeRptByEmpMonthDTO getWorkRptWorkTypeRptByEmpMonthDTO() {
		return workRptWorkTypeRptByEmpMonthDTO;
	}

	public void setWorkRptWorkTypeRptByEmpMonthDTO(WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO) {
		this.workRptWorkTypeRptByEmpMonthDTO = workRptWorkTypeRptByEmpMonthDTO;
	}

	public WorkRptWorkTypeRptByEmpCommonDTO getWorkRptWorkTypeRptByEmpCommonDTO() {
		return workRptWorkTypeRptByEmpCommonDTO;
	}

	public void setWorkRptWorkTypeRptByEmpCommonDTO(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO) {
		this.workRptWorkTypeRptByEmpCommonDTO = workRptWorkTypeRptByEmpCommonDTO;
	}
    
}
