package dream.work.cal.pmdinsmonth.form;

import common.struts.BaseForm;

import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * ������������- �� Form
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmDInsMonthDetailForm"
 */
public class WorkCalPmDInsMonthDetailForm extends BaseForm
{
    //========================================================================
    /** ������������ ���� */
    private WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = new WorkCalPmDInsMonthCommonDTO();
    //========================================================================
    /** ������������ �� */
    private WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = new WorkCalPmDInsMonthDetailDTO();
    
	public WorkCalPmDInsMonthCommonDTO getWorkCalPmDInsMonthCommonDTO() {
		return workCalPmDInsMonthCommonDTO;
	}
	public void setWorkCalPmDInsMonthCommonDTO(
			WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO) {
		this.workCalPmDInsMonthCommonDTO = workCalPmDInsMonthCommonDTO;
	}
	public WorkCalPmDInsMonthDetailDTO getWorkCalPmDInsMonthDetailDTO() {
		return workCalPmDInsMonthDetailDTO;
	}
	public void setWorkCalPmDInsMonthDetailDTO(
			WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO) {
		this.workCalPmDInsMonthDetailDTO = workCalPmDInsMonthDetailDTO;
	}

	



}
