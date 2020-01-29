package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 List 
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 *
 * @struts.form name="workPmMsTimeUInsListForm"
 */
public class WorkPmMsTimeUInsListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
    
    /** 작업시간 목록 DTO */
    private WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO = new WorkPmMsTimeUInsListDTO();
    /** 작업시간 상세 DTO */
    private WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = new WorkPmMsTimeUInsDetailDTO();
    
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
	public WorkPmMsTimeUInsListDTO getWorkPmMsTimeUInsListDTO() {
		return workPmMsTimeUInsListDTO;
	}
	public void setWorkPmMsTimeUInsListDTO(WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO) {
		this.workPmMsTimeUInsListDTO = workPmMsTimeUInsListDTO;
	}
	public WorkPmMsTimeUInsDetailDTO getWorkPmMsTimeUInsDetailDTO() {
		return workPmMsTimeUInsDetailDTO;
	}
	public void setWorkPmMsTimeUInsDetailDTO(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO) {
		this.workPmMsTimeUInsDetailDTO = workPmMsTimeUInsDetailDTO;
	}
    
	
}
