package dream.work.cal.pmrinsmonth.form;

import common.struts.BaseForm;

import dream.work.cal.pmrinsmonth.dto.WorkCalPmRInsMonthCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * ������������ - ��� form
 * @author  kim21017
 * @version $Id: WorkCalPmRInsMonthListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmRInsMonthListForm"
 */
public class WorkCalPmRInsMonthListForm extends BaseForm
{
    //===============================================================
    /** ������������ ���� */
    private WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO = new WorkCalPmRInsMonthCommonDTO();
    /** �����۾�����(�Ⱓ) ���� */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
    
    /** �۾���� ���� */ 
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();

    
	public WorkCalPmRInsMonthCommonDTO getWorkCalPmRInsMonthCommonDTO() {
		return workCalPmRInsMonthCommonDTO;
	}

	public void setWorkCalPmRInsMonthCommonDTO(
			WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO) {
		this.workCalPmRInsMonthCommonDTO = workCalPmRInsMonthCommonDTO;
	}

	public WorkCalPmRInsPeriodCommonDTO getWorkCalPmRInsPeriodCommonDTO() {
		return workCalPmRInsPeriodCommonDTO;
	}

	public void setWorkCalPmRInsPeriodCommonDTO(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO) {
		this.workCalPmRInsPeriodCommonDTO = workCalPmRInsPeriodCommonDTO;
	}

	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	

}
