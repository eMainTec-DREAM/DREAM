package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * �������� - ��� form
 * @author  jung7126
 * @version $Id: MaPmMstrListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrListForm"
 */
public class MaPmMstrListForm extends BaseForm
{    
    //===============================================================
    /** �������� ���� */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    
	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}
}
