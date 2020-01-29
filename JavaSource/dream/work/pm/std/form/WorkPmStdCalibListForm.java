package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
/**
 * 교정표준값 타입 - List Form
 * @author kim21017
 * @version $Id: WorkPmStdCalibListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workPmStdCalibListForm"
 * */

public class WorkPmStdCalibListForm extends BaseForm{
	
	private WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = new WorkPmStdCalibCommonDTO();

	public WorkPmStdCalibCommonDTO getWorkPmStdCalibCommonDTO() {
		return workPmStdCalibCommonDTO;
	}

	public void setWorkPmStdCalibCommonDTO(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO) {
		this.workPmStdCalibCommonDTO = workPmStdCalibCommonDTO;
	}
	
}
