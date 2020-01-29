package dream.work.cal.pmrinsperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * �����۾�����(�Ⱓ)- �� Form
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmRInsPeriodDetailForm"
 */
public class WorkCalPmRInsPeriodDetailForm extends BaseForm
{
    //========================================================================
    /** �����۾�����(�Ⱓ) ���� */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
    //========================================================================
    /** �����۾�����(�Ⱓ) �� */
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
