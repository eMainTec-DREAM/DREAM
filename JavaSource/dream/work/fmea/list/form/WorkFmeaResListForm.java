package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
/**
 * 고장영향성평가 - List Form
 * @author kim21017
 * @version $Id: WorkFmeaResListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaResListForm"
 * */

public class WorkFmeaResListForm extends BaseForm{
	
	private WorkFmeaResCommonDTO workFmeaResCommonDTO = new WorkFmeaResCommonDTO();

	public WorkFmeaResCommonDTO getWorkFmeaResCommonDTO() {
		return workFmeaResCommonDTO;
	}

	public void setWorkFmeaResCommonDTO(WorkFmeaResCommonDTO workFmeaResCommonDTO) {
		this.workFmeaResCommonDTO = workFmeaResCommonDTO;
	}
	
}
