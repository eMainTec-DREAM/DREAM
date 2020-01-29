package dream.work.cal.pminsappr.form;

import common.struts.BaseForm;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprSchedListDTO;

/**
 * 예방점검계획승인 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workCalPmInsApprSchedListForm"
 */
public class WorkCalPmInsApprSchedListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
    /** 상세 */
    private WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = new WorkCalPmInsApprDetailDTO();
    /** 점검작업 */
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
