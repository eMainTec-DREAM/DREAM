package dream.part.pur.po.form;

import common.struts.BaseForm;
import dream.part.pur.po.dto.MaPtPoCommonDTO;

/**
 * 발주이력 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPoListForm"
 */
public class MaPtPoListForm extends BaseForm
{    
    //===============================================================
    /** 발주이력 공통 */
    private MaPtPoCommonDTO maPtPoCommonDTO = new MaPtPoCommonDTO();

	public MaPtPoCommonDTO getMaPtPoCommonDTO() {
		return maPtPoCommonDTO;
	}

	public void setMaPtPoCommonDTO(MaPtPoCommonDTO maPtPoCommonDTO) {
		this.maPtPoCommonDTO = maPtPoCommonDTO;
	}
}
