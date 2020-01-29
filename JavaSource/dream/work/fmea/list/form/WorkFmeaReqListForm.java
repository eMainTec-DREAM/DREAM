package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
/**
 * 고장영향성평가 - List Form
 * @author kim21017
 * @version $Id: WorkFmeaReqListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaReqListForm"
 * */

public class WorkFmeaReqListForm extends BaseForm{
	
	private WorkFmeaReqCommonDTO workFmeaReqCommonDTO = new WorkFmeaReqCommonDTO();

	public WorkFmeaReqCommonDTO getWorkFmeaReqCommonDTO() {
		return workFmeaReqCommonDTO;
	}

	public void setWorkFmeaReqCommonDTO(WorkFmeaReqCommonDTO workFmeaReqCommonDTO) {
		this.workFmeaReqCommonDTO = workFmeaReqCommonDTO;
	}
	
}
