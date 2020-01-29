package dream.work.cal.pmrinsyear.form;

import common.struts.BaseForm;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * �����۾����� - ��� form
 * @author  kim21017
 * @version $Id: WorkCalPmRInsYearListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmRInsYearListForm"
 */
public class WorkCalPmRInsYearListForm extends BaseForm
{
    //===============================================================
    /** �����۾����� ���� */
    private WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO = new WorkCalPmRInsYearCommonDTO();
    //===============================================================
    /** �����۾�����(�Ⱓ) ���� */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
    
    /** �۾���� ���� */ 
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();

    
	public WorkCalPmRInsYearCommonDTO getWorkCalPmRInsYearCommonDTO() {
		return workCalPmRInsYearCommonDTO;
	}

	public void setWorkCalPmRInsYearCommonDTO(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO) {
		this.workCalPmRInsYearCommonDTO = workCalPmRInsYearCommonDTO;
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
