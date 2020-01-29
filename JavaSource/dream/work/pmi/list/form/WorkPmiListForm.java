package dream.work.pmi.list.form;

import common.struts.BaseForm;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * 점검작업 - 목록 form
 * @author  kim21017
 * @version $Id: WorkPmiListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmiListForm"
 */
public class WorkPmiListForm extends BaseForm
{    
    //===============================================================
    /** 작업결과 공통 */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}
}
