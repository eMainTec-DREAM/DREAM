package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * ������ 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workPmListShiftListForm"
 */
public class WorkPmListShiftListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();


	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
