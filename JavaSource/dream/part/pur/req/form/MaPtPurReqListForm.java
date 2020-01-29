package dream.part.pur.req.form;

import common.struts.BaseForm;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 何前荐府 - 格废 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPurReqListForm"
 */
public class MaPtPurReqListForm extends BaseForm
{    
    //===============================================================
    /** 何前荐府 傍烹 */
    private MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
    
	public MaPtReqCommonDTO getMaPtReqCommonDTO() {
		return maPtReqCommonDTO;
	}

	public void setMaPtReqCommonDTO(MaPtReqCommonDTO maPtReqCommonDTO) {
		this.maPtReqCommonDTO = maPtReqCommonDTO;
	}
}
