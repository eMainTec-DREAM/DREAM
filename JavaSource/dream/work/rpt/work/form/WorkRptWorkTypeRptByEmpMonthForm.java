package dream.work.rpt.work.form;

import common.struts.BaseForm;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;

/**
 * ����ں��۾���Ȳ - ����� �����۾���Ȳ �� ��� 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWorkTypeRptByEmpMonthForm"
 */
public class WorkRptWorkTypeRptByEmpMonthForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
