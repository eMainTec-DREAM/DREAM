package dream.work.rpt.pm.monthnew.form;

import common.struts.BaseForm;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * 신규점검등록현황 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workRptPmMonthNewListForm"
 */
public class WorkRptPmMonthNewListForm extends BaseForm
{    
    private WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO = new WorkRptPmMonthNewListDTO();

	public WorkRptPmMonthNewListDTO getWorkRptPmMonthNewListDTO() {
		return workRptPmMonthNewListDTO;
	}

	public void setWorkRptPmMonthNewListDTO(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO) {
		this.workRptPmMonthNewListDTO = workRptPmMonthNewListDTO;
	}

}
