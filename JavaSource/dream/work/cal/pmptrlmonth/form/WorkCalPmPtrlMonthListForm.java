package dream.work.cal.pmptrlmonth.form;

import common.struts.BaseForm;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;

/**
 * ������������ - ��� form
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListForm.java,v 1.0 2017/09/24 09:13:09 youngjoo38 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalPmPtrlMonthListForm"
 */
public class WorkCalPmPtrlMonthListForm extends BaseForm
{
    //===============================================================
    /** ������������ ���� */
    private WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO = new WorkCalPmPtrlMonthCommonDTO();

	public WorkCalPmPtrlMonthCommonDTO getWorkCalPmPtrlMonthCommonDTO() {
		return workCalPmPtrlMonthCommonDTO;
	}

	public void setWorkCalPmPtrlMonthCommonDTO(
			WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO) {
		this.workCalPmPtrlMonthCommonDTO = workCalPmPtrlMonthCommonDTO;
	}

}
