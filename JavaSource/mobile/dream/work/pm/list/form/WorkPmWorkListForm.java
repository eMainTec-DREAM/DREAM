package mobile.dream.work.pm.list.form;

import common.struts.BaseForm;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;

/**
 * 계획작업 - 목록 form
 * @author  jung7126
 * @version $Id: WorkPmWorkListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmWorkListForm"
 */
public class WorkPmWorkListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkPmWorkCommonDTO workPmWorkCommonDTO = new WorkPmWorkCommonDTO();
    
	public WorkPmWorkCommonDTO getWorkPmWorkCommonDTO() {
		return workPmWorkCommonDTO;
	}

	public void setWorkPmWorkCommonDTO(WorkPmWorkCommonDTO workPmWorkCommonDTO) {
		this.workPmWorkCommonDTO = workPmWorkCommonDTO;
	}
}
