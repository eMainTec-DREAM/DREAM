package dream.work.rpt.pm.month.rate.form;

import common.struts.BaseForm;
import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;

/**
 * 월별점검실행율 FORM
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListForm.java,v 1.0 2015/12/01 09:13:09 sy.yang Exp $
 * @since   1.0
 *
 * @struts.form name="workRptPmMonthRateListForm"
 */
public class WorkRptPmMonthRateListForm extends BaseForm
{    
    private WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO = new WorkRptPmMonthRateListDTO();

	public WorkRptPmMonthRateListDTO getWorkRptPmMonthRateListDTO() {
		return workRptPmMonthRateListDTO;
	}

	public void setWorkRptPmMonthRateListDTO(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO) {
		this.workRptPmMonthRateListDTO = workRptPmMonthRateListDTO;
	}

}
