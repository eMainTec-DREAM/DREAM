package dream.work.fmea.list.form;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaDetailDTO;

/**
 * 고장영향성평가 - Detail Form
 * @author kim21017
 * @version $Id: WorkFmeaDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="workFmeaDetailForm"
 */
public class WorkFmeaDetailForm extends BaseForm
{
	private WorkFmeaCommonDTO workFmeaCommonDTO = new WorkFmeaCommonDTO();
	private WorkFmeaDetailDTO workFmeaDetailDTO = new WorkFmeaDetailDTO();
	
	/** 결재 진행 이력 DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
	public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }
    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }
    public WorkFmeaCommonDTO getWorkFmeaCommonDTO() {
		return workFmeaCommonDTO;
	}
	public void setWorkFmeaCommonDTO(WorkFmeaCommonDTO workFmeaCommonDTO) {
		this.workFmeaCommonDTO = workFmeaCommonDTO;
	}
	public WorkFmeaDetailDTO getWorkFmeaDetailDTO() {
		return workFmeaDetailDTO;
	}
	public void setWorkFmeaDetailDTO(WorkFmeaDetailDTO workFmeaDetailDTO) {
		this.workFmeaDetailDTO = workFmeaDetailDTO;
	}
}
