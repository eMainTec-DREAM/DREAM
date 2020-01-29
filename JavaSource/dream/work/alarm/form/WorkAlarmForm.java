package dream.work.alarm.form;

import common.struts.BaseForm;
import dream.work.alarm.dto.WorkAlarmDTO;

/**
 * Alarm List - ��� form 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workAlarmForm"
 */
public class WorkAlarmForm extends BaseForm
{    
    //=======================================================================
    /** Alarm List ���� */
    private WorkAlarmDTO workAlarmDTO = new WorkAlarmDTO();
    //=======================================================================

	public WorkAlarmDTO getWorkAlarmDTO() {
		return workAlarmDTO;
	}

	public void setWorkAlarmDTO(WorkAlarmDTO workAlarmDTO) {
		this.workAlarmDTO = workAlarmDTO;
	}
}
