package dream.part.pur.po.form;

import common.struts.BaseForm;
import dream.part.pur.po.dto.MaPtPoCommonDTO;

/**
 * �����̷� - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPoListForm"
 */
public class MaPtPoListForm extends BaseForm
{    
    //===============================================================
    /** �����̷� ���� */
    private MaPtPoCommonDTO maPtPoCommonDTO = new MaPtPoCommonDTO();

	public MaPtPoCommonDTO getMaPtPoCommonDTO() {
		return maPtPoCommonDTO;
	}

	public void setMaPtPoCommonDTO(MaPtPoCommonDTO maPtPoCommonDTO) {
		this.maPtPoCommonDTO = maPtPoCommonDTO;
	}
}
