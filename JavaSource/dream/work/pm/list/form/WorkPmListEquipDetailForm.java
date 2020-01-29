package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * ���漳�� 
 * @author  kim21017
 * @version $Id: WorkPmListEquipDetailForm.java,v 1.0 2015/12/04 09:09:54 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmListEquipDetailForm"
 */
public class WorkPmListEquipDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** ������� �������� �� DTO  */
    private WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();

	public WorkPmListEquipDetailDTO getWorkPmListEquipDetailDTO() {
		return workPmListEquipDetailDTO;
	}
	public void setWorkPmListEquipDetailDTO(WorkPmListEquipDetailDTO workPmListEquipDetailDTO) {
		this.workPmListEquipDetailDTO = workPmListEquipDetailDTO;
	}
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}
	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
