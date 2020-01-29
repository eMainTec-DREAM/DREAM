package dream.work.pm.std.form;

import dream.comm.form.MaFinderAcForm;
import dream.work.pm.std.dto.WorkPmStdCalibValLovDTO;
/**
 * 교정표준값 LOV - List Form
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workPmStdCalibValLovForm"
 * */

public class WorkPmStdCalibValLovForm extends MaFinderAcForm{
	
	private WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO = new WorkPmStdCalibValLovDTO();

	public WorkPmStdCalibValLovDTO getWorkPmStdCalibValLovDTO() {
		return workPmStdCalibValLovDTO;
	}

	public void setWorkPmStdCalibValLovDTO(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO) {
		this.workPmStdCalibValLovDTO = workPmStdCalibValLovDTO;
	}

	
}
