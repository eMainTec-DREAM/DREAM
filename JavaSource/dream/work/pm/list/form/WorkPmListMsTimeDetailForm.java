package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;

/**
 * �۾��ð� detail 
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 *
 * @struts.form name="workPmListMsTimeDetailForm"
 */
public class WorkPmListMsTimeDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
    
    /** �۾��ð� ��� DTO */
    private WorkPmListMsTimeListDTO workPmListMsTimeListDTO = new WorkPmListMsTimeListDTO();
    
	/** �۾��ð� �� DTO */
    private WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = new WorkPmListMsTimeDetailDTO();

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}

	public MaPmMstrDetailDTO getMaPmMstrDetailDTO() {
		return maPmMstrDetailDTO;
	}

	public void setMaPmMstrDetailDTO(MaPmMstrDetailDTO maPmMstrDetailDTO) {
		this.maPmMstrDetailDTO = maPmMstrDetailDTO;
	}

	public WorkPmListMsTimeListDTO getWorkPmListMsTimeListDTO() {
		return workPmListMsTimeListDTO;
	}

	public void setWorkPmListMsTimeListDTO(WorkPmListMsTimeListDTO workPmListMsTimeListDTO) {
		this.workPmListMsTimeListDTO = workPmListMsTimeListDTO;
	}

	public WorkPmListMsTimeDetailDTO getWorkPmListMsTimeDetailDTO() {
		return workPmListMsTimeDetailDTO;
	}

	public void setWorkPmListMsTimeDetailDTO(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO) {
		this.workPmListMsTimeDetailDTO = workPmListMsTimeDetailDTO;
	}
    
    
}
