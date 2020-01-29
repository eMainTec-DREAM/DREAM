package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;

/**
 * 사용량 항목 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workPmPointUInsListForm"
 * */
public class WorkPmPointUInsListForm extends BaseForm{
	
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO = new WorkPmPointUInsDetailDTO();
    
	public WorkPmPointUInsDetailDTO getWorkPmPointUInsDetailDTO() {
		return workPmPointUInsDetailDTO;
	}

	public void setWorkPmPointUInsDetailDTO(WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO) {
		this.workPmPointUInsDetailDTO = workPmPointUInsDetailDTO;
	}

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
