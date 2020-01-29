package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValDetailDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;

/**
 * 표준교정값 - Detail Form
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workPmStdCalibValDetailForm"
 */
public class WorkPmStdCalibValDetailForm extends BaseForm
{
	private WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = new WorkPmStdCalibCommonDTO();
	private WorkPmStdCalibValListDTO workPmStdCalibValListDTO = new WorkPmStdCalibValListDTO();
	private WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO = new WorkPmStdCalibValDetailDTO();
	
	public WorkPmStdCalibCommonDTO getWorkPmStdCalibCommonDTO() {
		return workPmStdCalibCommonDTO;
	}
	public void setWorkPmStdCalibCommonDTO(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO) {
		this.workPmStdCalibCommonDTO = workPmStdCalibCommonDTO;
	}
	public WorkPmStdCalibValListDTO getWorkPmStdCalibValListDTO() {
		return workPmStdCalibValListDTO;
	}
	public void setWorkPmStdCalibValListDTO(WorkPmStdCalibValListDTO workPmStdCalibValListDTO) {
		this.workPmStdCalibValListDTO = workPmStdCalibValListDTO;
	}
	public WorkPmStdCalibValDetailDTO getWorkPmStdCalibValDetailDTO() {
		return workPmStdCalibValDetailDTO;
	}
	public void setWorkPmStdCalibValDetailDTO(WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO) {
		this.workPmStdCalibValDetailDTO = workPmStdCalibValDetailDTO;
	}
}
