package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaReqDetailDTO;

/**
 * 고장영향성평가 - Detail Form
 * @author kim21017
 * @version $Id: WorkFmeaReqDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaReqDetailForm"
 */
public class WorkFmeaReqDetailForm extends BaseForm
{
	private WorkFmeaReqCommonDTO workFmeaReqCommonDTO = new WorkFmeaReqCommonDTO();
	private WorkFmeaReqDetailDTO workFmeaReqDetailDTO = new WorkFmeaReqDetailDTO();
    
	public WorkFmeaReqCommonDTO getWorkFmeaReqCommonDTO() {
		return workFmeaReqCommonDTO;
	}
	public void setWorkFmeaReqCommonDTO(WorkFmeaReqCommonDTO workFmeaReqCommonDTO) {
		this.workFmeaReqCommonDTO = workFmeaReqCommonDTO;
	}
	public WorkFmeaReqDetailDTO getWorkFmeaReqDetailDTO() {
		return workFmeaReqDetailDTO;
	}
	public void setWorkFmeaReqDetailDTO(WorkFmeaReqDetailDTO workFmeaReqDetailDTO) {
		this.workFmeaReqDetailDTO = workFmeaReqDetailDTO;
	}
}
