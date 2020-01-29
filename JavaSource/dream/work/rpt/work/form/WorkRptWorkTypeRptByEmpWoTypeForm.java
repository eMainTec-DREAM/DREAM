package dream.work.rpt.work.form;

import common.struts.BaseForm;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * 담당자별작업현황 - 담당자 작업종류별현황 탭 목록 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWorkTypeRptByEmpWoTypeForm"
 */
public class WorkRptWorkTypeRptByEmpWoTypeForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO = new WorkRptWorkTypeRptByEmpCommonDTO();
    
    private WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO = new WorkRptWorkTypeRptByEmpWoTypeDTO();

	public WorkRptWorkTypeRptByEmpCommonDTO getWorkRptWorkTypeRptByEmpCommonDTO() {
		return workRptWorkTypeRptByEmpCommonDTO;
	}

	public void setWorkRptWorkTypeRptByEmpCommonDTO(WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO) {
		this.workRptWorkTypeRptByEmpCommonDTO = workRptWorkTypeRptByEmpCommonDTO;
	}

	public WorkRptWorkTypeRptByEmpWoTypeDTO getWorkRptWorkTypeRptByEmpWoTypeDTO() {
		return workRptWorkTypeRptByEmpWoTypeDTO;
	}

	public void setWorkRptWorkTypeRptByEmpWoTypeDTO(WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO) {
		this.workRptWorkTypeRptByEmpWoTypeDTO = workRptWorkTypeRptByEmpWoTypeDTO;
	}
    
}
