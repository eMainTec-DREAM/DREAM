package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.WorkPmListSchdDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * �����۾� ���� �� 
 * @author  kim21017
 * @version $Id: WorkPmListSchdDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmListSchdDetailForm"
 */
public class WorkPmListSchdDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** ������� �������� �� DTO  */
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
