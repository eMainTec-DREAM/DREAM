package dream.work.cal.pmrinsperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * 예방작업일정(기간) - 목록 form
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmRInsPeriodListForm"
 */
public class WorkCalPmRInsPeriodListForm extends BaseForm
{
    //===============================================================
    /** 예방작업일정(기간) 공통 */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();

    /** 작업결과 공통 */ 
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();

    
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public WorkCalPmRInsPeriodCommonDTO getWorkCalPmRInsPeriodCommonDTO() {
		return workCalPmRInsPeriodCommonDTO;
	}

	public void setWorkCalPmRInsPeriodCommonDTO(
			WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO) {
		this.workCalPmRInsPeriodCommonDTO = workCalPmRInsPeriodCommonDTO;
	}

	

}
