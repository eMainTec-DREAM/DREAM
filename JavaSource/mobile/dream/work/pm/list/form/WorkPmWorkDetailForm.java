package mobile.dream.work.pm.list.form;

import common.struts.BaseForm;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;

/**
 * 상세 Form
 * @author  jung7126
 * @version $Id: WorkPmWorkDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmWorkDetailForm"
 */
public class WorkPmWorkDetailForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private WorkPmWorkCommonDTO workPmWorkCommonDTO = new WorkPmWorkCommonDTO();
    //========================================================================
    /** 상세 */
    private WorkPmWorkDetailDTO workPmWorkDetailDTO = new WorkPmWorkDetailDTO();
    

	public WorkPmWorkCommonDTO getWorkPmWorkCommonDTO() {
		return workPmWorkCommonDTO;
	}

    public void setWorkPmWorkCommonDTO(WorkPmWorkCommonDTO workPmWorkCommonDTO) {
		this.workPmWorkCommonDTO = workPmWorkCommonDTO;
	}

	public WorkPmWorkDetailDTO getWorkPmWorkDetailDTO() {
		return workPmWorkDetailDTO;
	}

	public void setWorkPmWorkDetailDTO(WorkPmWorkDetailDTO workPmWorkDetailDTO) {
		this.workPmWorkDetailDTO = workPmWorkDetailDTO;
	}
}
