package dream.work.cal.pminsappr.form;

import common.struts.BaseForm;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprSchedListDTO;

/**
 * �������˰�ȹ���� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workCalPmInsApprSchedListForm"
 */
public class WorkCalPmInsApprSchedListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
    /** �� */
    private WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = new WorkCalPmInsApprDetailDTO();
    /** �����۾� */
    private WorkCalPmInsApprSchedListDTO workCalPmInsApprSchedListDTO = new WorkCalPmInsApprSchedListDTO();
    
    
	public WorkCalPmInsApprDetailDTO getWorkCalPmInsApprDetailDTO() {
		return workCalPmInsApprDetailDTO;
	}
	public void setWorkCalPmInsApprDetailDTO(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO) {
		this.workCalPmInsApprDetailDTO = workCalPmInsApprDetailDTO;
	}
	public WorkCalPmInsApprCommonDTO getWorkCalPmInsApprCommonDTO() {
		return workCalPmInsApprCommonDTO;
	}
	public void setWorkCalPmInsApprCommonDTO(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO) {
		this.workCalPmInsApprCommonDTO = workCalPmInsApprCommonDTO;
	}
	public WorkCalPmInsApprSchedListDTO getWorkCalPmInsApprSchedListDTO() {
		return workCalPmInsApprSchedListDTO;
	}
	public void setWorkCalPmInsApprSchedListDTO(WorkCalPmInsApprSchedListDTO workCalPmInsApprSchedListDTO) {
		this.workCalPmInsApprSchedListDTO = workCalPmInsApprSchedListDTO;
	}
	
}
