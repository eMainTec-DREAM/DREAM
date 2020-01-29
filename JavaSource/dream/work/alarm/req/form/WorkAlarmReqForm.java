package dream.work.alarm.req.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.work.alarm.dto.WorkAlarmDTO;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;

/**
 * ������û ���� - ��� form 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workAlarmReqForm"
 */
public class WorkAlarmReqForm extends BaseForm
{    
    //=======================================================================
    /** Alarm List ���� */
    private WorkAlarmDTO workAlarmDTO = new WorkAlarmDTO();
    //=======================================================================
    /** Alarm �۾���û */
    private WorkAlarmReqDTO workAlarmReqDTO = new WorkAlarmReqDTO();
    
    /** �۾���û ���� */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    
    /** �۾����� �� */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();

	public WorkAlarmDTO getWorkAlarmDTO() {
		return workAlarmDTO;
	}

	public void setWorkAlarmDTO(WorkAlarmDTO workAlarmDTO) {
		this.workAlarmDTO = workAlarmDTO;
	}

	public WorkAlarmReqDTO getWorkAlarmReqDTO() {
		return workAlarmReqDTO;
	}

	public void setWorkAlarmReqDTO(WorkAlarmReqDTO workAlarmReqDTO) {
		this.workAlarmReqDTO = workAlarmReqDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public MaWoReqDetailDTO getMaWoReqDetailDTO() {
		return maWoReqDetailDTO;
	}

	public void setMaWoReqDetailDTO(MaWoReqDetailDTO maWoReqDetailDTO) {
		this.maWoReqDetailDTO = maWoReqDetailDTO;
	}
}
