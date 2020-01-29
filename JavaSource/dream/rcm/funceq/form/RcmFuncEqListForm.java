package dream.rcm.funceq.form;

import common.struts.BaseForm;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 질의 - 목록 form
 * @author  kim21017
 * @version $Id: RcmFuncEqListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFuncEqListForm"
 */
public class RcmFuncEqListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private RcmFuncEqCommonDTO rcmFuncEqCommonDTO = new RcmFuncEqCommonDTO();
    
	public RcmFuncEqCommonDTO getRcmFuncEqCommonDTO() {
		return rcmFuncEqCommonDTO;
	}

	public void setRcmFuncEqCommonDTO(RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		this.rcmFuncEqCommonDTO = rcmFuncEqCommonDTO;
	}
}
