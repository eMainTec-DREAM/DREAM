package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaResDetailDTO;

/**
 * 고장영향성평가 - Detail Form
 * @author kim21017
 * @version $Id: WorkFmeaResDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaResDetailForm"
 */
public class WorkFmeaResDetailForm extends BaseForm
{
	private WorkFmeaResCommonDTO workFmeaResCommonDTO = new WorkFmeaResCommonDTO();
	private WorkFmeaResDetailDTO workFmeaResDetailDTO = new WorkFmeaResDetailDTO();
    
	public WorkFmeaResCommonDTO getWorkFmeaResCommonDTO() {
		return workFmeaResCommonDTO;
	}
	public void setWorkFmeaResCommonDTO(WorkFmeaResCommonDTO workFmeaResCommonDTO) {
		this.workFmeaResCommonDTO = workFmeaResCommonDTO;
	}
	public WorkFmeaResDetailDTO getWorkFmeaResDetailDTO() {
		return workFmeaResDetailDTO;
	}
	public void setWorkFmeaResDetailDTO(WorkFmeaResDetailDTO workFmeaResDetailDTO) {
		this.workFmeaResDetailDTO = workFmeaResDetailDTO;
	}
}
