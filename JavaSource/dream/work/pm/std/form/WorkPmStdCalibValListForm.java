package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;
/**
 * 표준교정값 - List Form
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workPmStdCalibValListForm"
 * */

public class WorkPmStdCalibValListForm extends BaseForm{
	
	private WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = new WorkPmStdCalibCommonDTO();
	private WorkPmStdCalibValListDTO workPmStdCalibValListDTO = new WorkPmStdCalibValListDTO();
	
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
	
}
