package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;

/**
 * ������ 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workPmListShiftDetailForm"
 */
public class WorkPmListShiftDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** ������� �������� �� DTO  */
    private WorkPmListShiftDetailDTO workPmListShiftDetailDTO = new WorkPmListShiftDetailDTO();
    
	public WorkPmListShiftDetailDTO getWorkPmListShiftDetailDTO()
    {
        return workPmListShiftDetailDTO;
    }
    public void setWorkPmListShiftDetailDTO(
            WorkPmListShiftDetailDTO workPmListShiftDetailDTO)
    {
        this.workPmListShiftDetailDTO = workPmListShiftDetailDTO;
    }
    public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
