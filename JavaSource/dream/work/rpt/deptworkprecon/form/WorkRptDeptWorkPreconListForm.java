package dream.work.rpt.deptworkprecon.form;

import common.struts.BaseForm;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;

/**
 * �μ��� �۾�������Ȳ 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workRptDeptWorkPreconListForm"
 */
public class WorkRptDeptWorkPreconListForm extends BaseForm
{    
    private WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO = new WorkRptDeptWorkPreconListDTO();

	public WorkRptDeptWorkPreconListDTO getWorkRptDeptWorkPreconListDTO() {
		return workRptDeptWorkPreconListDTO;
	}

	public void setWorkRptDeptWorkPreconListDTO(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO) {
		this.workRptDeptWorkPreconListDTO = workRptDeptWorkPreconListDTO;
	}

}
