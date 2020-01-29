package dream.work.cal.pmdinsmonth.form;

import common.struts.BaseForm;

import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;

/**
 * ������������ - ��� form
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmDInsMonthListForm"
 */
public class WorkCalPmDInsMonthListForm extends BaseForm
{
    //===============================================================
    /** ������������ ���� */
    private WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = new WorkCalPmDInsMonthCommonDTO();

    private WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
    
    
	public WorkPmiDInsCommonDTO getWorkPmiDInsCommonDTO() {
		return workPmiDInsCommonDTO;
	}

	public void setWorkPmiDInsCommonDTO(WorkPmiDInsCommonDTO workPmiDInsCommonDTO) {
		this.workPmiDInsCommonDTO = workPmiDInsCommonDTO;
	}

	public WorkCalPmDInsMonthCommonDTO getWorkCalPmDInsMonthCommonDTO() {
		return workCalPmDInsMonthCommonDTO;
	}

	public void setWorkCalPmDInsMonthCommonDTO(
			WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO) {
		this.workCalPmDInsMonthCommonDTO = workCalPmDInsMonthCommonDTO;
	}

	



}
