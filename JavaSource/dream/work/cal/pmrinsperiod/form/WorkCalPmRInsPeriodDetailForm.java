package dream.work.cal.pmrinsperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간)- 상세 Form
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmRInsPeriodDetailForm"
 */
public class WorkCalPmRInsPeriodDetailForm extends BaseForm
{
    //========================================================================
    /** 예방작업일정(기간) 공통 */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
    //========================================================================
    /** 예방작업일정(기간) 상세 */
    private WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = new WorkCalPmRInsPeriodDetailDTO();
    
	public WorkCalPmRInsPeriodCommonDTO getWorkCalPmRInsPeriodCommonDTO() {
		return workCalPmRInsPeriodCommonDTO;
	}
	public void setWorkCalPmRInsPeriodCommonDTO(
			WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO) {
		this.workCalPmRInsPeriodCommonDTO = workCalPmRInsPeriodCommonDTO;
	}
	public WorkCalPmRInsPeriodDetailDTO getWorkCalPmRInsPeriodDetailDTO() {
		return workCalPmRInsPeriodDetailDTO;
	}
	public void setWorkCalPmRInsPeriodDetailDTO(
			WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO) {
		this.workCalPmRInsPeriodDetailDTO = workCalPmRInsPeriodDetailDTO;
	}



}
