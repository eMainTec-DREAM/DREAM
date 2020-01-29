package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibDetailDTO;

/**
 * 교정표준값 타입 - Detail Form
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workPmStdCalibDetailForm"
 */
public class WorkPmStdCalibDetailForm extends BaseForm
{
	private WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = new WorkPmStdCalibCommonDTO();
	private WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO = new WorkPmStdCalibDetailDTO();
    
	public WorkPmStdCalibCommonDTO getWorkPmStdCalibCommonDTO() {
		return workPmStdCalibCommonDTO;
	}
	public void setWorkPmStdCalibCommonDTO(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO) {
		this.workPmStdCalibCommonDTO = workPmStdCalibCommonDTO;
	}
	public WorkPmStdCalibDetailDTO getWorkPmStdCalibDetailDTO() {
		return workPmStdCalibDetailDTO;
	}
	public void setWorkPmStdCalibDetailDTO(WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO) {
		this.workPmStdCalibDetailDTO = workPmStdCalibDetailDTO;
	}
}
