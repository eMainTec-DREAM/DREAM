package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
/**
 * 고장영향성평가 - List Form
 * @author kim21017
 * @version $Id: WorkFmeaListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaListForm"
 * */

public class WorkFmeaListForm extends BaseForm{
	
	private WorkFmeaCommonDTO workFmeaCommonDTO = new WorkFmeaCommonDTO();

	public WorkFmeaCommonDTO getWorkFmeaCommonDTO() {
		return workFmeaCommonDTO;
	}

	public void setWorkFmeaCommonDTO(WorkFmeaCommonDTO workFmeaCommonDTO) {
		this.workFmeaCommonDTO = workFmeaCommonDTO;
	}
	
}
