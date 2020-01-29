package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;

/**
 * 작업시간 List 
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 *
 * @struts.form name="workPmListMsTimeListForm"
 */
public class WorkPmListMsTimeListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
    
    /** 작업시간 목록 DTO */
    private WorkPmListMsTimeListDTO workPmListMsTimeListDTO = new WorkPmListMsTimeListDTO();
    /** 작업시간 상세 DTO */
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
