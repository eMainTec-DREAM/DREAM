package dream.work.rpt.work.form;

import common.struts.BaseForm;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;

/**
 * ����ں��۾���Ȳ - ����� �۾���������Ȳ �� ��� 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWorkTypeRptByEmpWoTypeForm"
 */
public class WorkRptWorkTypeRptByEmpWoTypeForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
