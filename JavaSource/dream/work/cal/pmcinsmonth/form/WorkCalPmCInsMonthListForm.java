package dream.work.cal.pmcinsmonth.form;

import common.struts.BaseForm;
import dream.work.cal.pmcinsmonth.dto.WorkCalPmCInsMonthCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;

/**
 * 월간예방일정 - 목록 form
 * @author  kim21017
 * @version $Id: WorkCalPmCInsMonthListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmCInsMonthListForm"
 */
public class WorkCalPmCInsMonthListForm extends BaseForm
{
    //===============================================================
    /** 월간예방일정 공통 */
    private WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO = new WorkCalPmCInsMonthCommonDTO();

    private WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = new WorkListCinsPlanMstrCommonDTO();
    
    private WorkPmiCInsCommonDTO workPmiCInsCommonDTO = new WorkPmiCInsCommonDTO();
    
    
	public WorkPmiCInsCommonDTO getWorkPmiCInsCommonDTO() {
		return workPmiCInsCommonDTO;
	}

	public void setWorkPmiCInsCommonDTO(WorkPmiCInsCommonDTO workPmiCInsCommonDTO) {
		this.workPmiCInsCommonDTO = workPmiCInsCommonDTO;
	}

	public WorkListCinsPlanMstrCommonDTO getWorkListCinsPlanMstrCommonDTO()
    {
        return workListCinsPlanMstrCommonDTO;
    }

    public void setWorkListCinsPlanMstrCommonDTO(
            WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO)
    {
        this.workListCinsPlanMstrCommonDTO = workListCinsPlanMstrCommonDTO;
    }

    public WorkCalPmCInsMonthCommonDTO getWorkCalPmCInsMonthCommonDTO() {
		return workCalPmCInsMonthCommonDTO;
	}

	public void setWorkCalPmCInsMonthCommonDTO(
			WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO) {
		this.workCalPmCInsMonthCommonDTO = workCalPmCInsMonthCommonDTO;
	}

	

}
