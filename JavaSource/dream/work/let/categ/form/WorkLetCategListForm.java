package dream.work.let.categ.form;

import common.struts.BaseForm;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 안전작업유형 LIST Page - List Form
 * @author euna0207
 * @version $Id: WorkLetCategListForm.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts.form name="workLetCategListForm"
 * */

public class WorkLetCategListForm extends BaseForm{
	
	private WorkLetCategCommonDTO workLetCategCommonDTO = new WorkLetCategCommonDTO();

	public WorkLetCategCommonDTO getWorkLetCategCommonDTO() {
		return workLetCategCommonDTO;
	}

	public void setWorkLetCategCommonDTO(WorkLetCategCommonDTO workLetCategCommonDTO) {
		this.workLetCategCommonDTO = workLetCategCommonDTO;
	}
	
	
}
