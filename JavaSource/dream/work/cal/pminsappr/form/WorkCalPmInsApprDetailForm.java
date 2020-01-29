package dream.work.cal.pminsappr.form;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * �������˰�ȹ����- �� Form
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workCalPmInsApprDetailForm"
 */
public class WorkCalPmInsApprDetailForm extends BaseForm
{
    /** ���� */ 
	private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
    /** �� */
    private WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = new WorkCalPmInsApprDetailDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    
	public AppReqCommonDTO getAppReqCommonDTO() {
		return appReqCommonDTO;
	}
	public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO) {
		this.appReqCommonDTO = appReqCommonDTO;
	}
	public WorkCalPmInsApprCommonDTO getWorkCalPmInsApprCommonDTO() {
		return workCalPmInsApprCommonDTO;
	}
	public void setWorkCalPmInsApprCommonDTO(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO) {
		this.workCalPmInsApprCommonDTO = workCalPmInsApprCommonDTO;
	}
	public WorkCalPmInsApprDetailDTO getWorkCalPmInsApprDetailDTO() {
		return workCalPmInsApprDetailDTO;
	}
	public void setWorkCalPmInsApprDetailDTO(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO) {
		this.workCalPmInsApprDetailDTO = workCalPmInsApprDetailDTO;
	}
    
}
