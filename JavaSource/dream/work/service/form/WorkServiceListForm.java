package dream.work.service.form;

import common.struts.BaseForm;
import dream.work.service.dto.WorkServiceCommonDTO;
/**
 * 서비스 마스터 목록 Form
 * @author cjscjs9
 * @version $Id: WorkServiceListForm.java,v 1.0 2018/07/27 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @struts.form name="workServiceListForm"
 * */

public class WorkServiceListForm extends BaseForm{
	
	private WorkServiceCommonDTO workServiceCommonDTO = new WorkServiceCommonDTO();

	public WorkServiceCommonDTO getWorkServiceCommonDTO() {
		return workServiceCommonDTO;
	}

	public void setWorkServiceCommonDTO(WorkServiceCommonDTO workServiceCommonDTO) {
		this.workServiceCommonDTO = workServiceCommonDTO;
	}
	
}
