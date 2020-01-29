package dream.tool.stk.form;

import common.struts.BaseForm;
import dream.tool.stk.dto.MaPttStckCommonDTO;

/**
 * ������� - ��� form
 * @author  ssong
 * @version $Id: MaPttStckListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPttStckListForm"
 */
public class MaPttStckListForm extends BaseForm
{    
    //===============================================================
    /** ������� ���� */
    private MaPttStckCommonDTO maPttStckCommonDTO = new MaPttStckCommonDTO();

	public MaPttStckCommonDTO getMaPttStckCommonDTO() {
		return maPttStckCommonDTO;
	}

	public void setMaPttStckCommonDTO(MaPttStckCommonDTO maPttStckCommonDTO) {
		this.maPttStckCommonDTO = maPttStckCommonDTO;
	}
}
