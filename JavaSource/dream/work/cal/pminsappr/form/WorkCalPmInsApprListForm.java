package dream.work.cal.pminsappr.form;

import common.struts.BaseForm;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;

/**
 * 예방점검계획승인 - 목록 form
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workCalPmInsApprListForm"
 */
public class WorkCalPmInsApprListForm extends BaseForm
{    
    /** 공통 */
    private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();

	public WorkCalPmInsApprCommonDTO getWorkCalPmInsApprCommonDTO() {
		return workCalPmInsApprCommonDTO;
	}

	public void setWorkCalPmInsApprCommonDTO(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO) {
		this.workCalPmInsApprCommonDTO = workCalPmInsApprCommonDTO;
	}
    
}
