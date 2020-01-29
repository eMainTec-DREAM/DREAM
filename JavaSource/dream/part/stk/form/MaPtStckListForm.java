package dream.part.stk.form;

import common.struts.BaseForm;
import dream.part.stk.dto.MaPtStckCommonDTO;

/**
 * ������� - ��� form
 * @author  ssong
 * @version $Id: MaPtStckListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtStckListForm"
 */
public class MaPtStckListForm extends BaseForm
{    
    //===============================================================
    /** ������� ���� */
    private MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();

	public MaPtStckCommonDTO getMaPtStckCommonDTO() {
		return maPtStckCommonDTO;
	}

	public void setMaPtStckCommonDTO(MaPtStckCommonDTO maPtStckCommonDTO) {
		this.maPtStckCommonDTO = maPtStckCommonDTO;
	}
}
