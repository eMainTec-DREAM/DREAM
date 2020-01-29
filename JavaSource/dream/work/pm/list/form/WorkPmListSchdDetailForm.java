package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업 일자 상세 
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmListSchdDetailForm"
 */
public class WorkPmListSchdDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** 사용자재 투입자재 상세 DTO  */
    private WorkPmListSchdDetailDTO workPmListSchdDetailDTO = new WorkPmListSchdDetailDTO();

	public WorkPmListSchdDetailDTO getWorkPmListSchdDetailDTO() {
		return workPmListSchdDetailDTO;
	}
	public void setWorkPmListSchdDetailDTO(WorkPmListSchdDetailDTO workPmListSchdDetailDTO) {
		this.workPmListSchdDetailDTO = workPmListSchdDetailDTO;
	}
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
