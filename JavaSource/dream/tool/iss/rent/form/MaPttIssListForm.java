package dream.tool.iss.rent.form;

import common.struts.BaseForm;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;

/**
 * 구매입고 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPttIssListForm"
 */
public class MaPttIssListForm extends BaseForm
{    
    //===============================================================
    /** 구매입고 공통 */
    private MaPttIssCommonDTO maPttIssCommonDTO = new MaPttIssCommonDTO();
    
	public MaPttIssCommonDTO getMaPttIssCommonDTO() {
		return maPttIssCommonDTO;
	}

	public void setMaPttIssCommonDTO(MaPttIssCommonDTO maPttIssCommonDTO) {
		this.maPttIssCommonDTO = maPttIssCommonDTO;
	}
}
