package dream.work.pm.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;

/**
 * 작업요청유형 팝업 Form
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workPmListMsTimeLovForm"
 */
public class WorkPmListMsTimeLovForm extends MaFinderAcForm
{
	private WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO  = new WorkPmListMsTimeLovDTO();

	public WorkPmListMsTimeLovDTO getWorkPmListMsTimeLovDTO() {
		return workPmListMsTimeLovDTO;
	}

	public void setWorkPmListMsTimeLovDTO(WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO) {
		this.workPmListMsTimeLovDTO = workPmListMsTimeLovDTO;
	}
}
