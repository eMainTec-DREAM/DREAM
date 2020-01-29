package dream.rcm.funceq.form;

import common.struts.BaseForm;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 답변- 목록
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFuncEqItemListForm"
 */
public class RcmFuncEqItemListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private RcmFuncEqCommonDTO rcmFuncEqCommonDTO = new RcmFuncEqCommonDTO();
    /** 답변  */
    private RcmFuncEqItemListDTO rcmFuncEqItemListDTO = new RcmFuncEqItemListDTO();
    
	public RcmFuncEqCommonDTO getRcmFuncEqCommonDTO() {
		return rcmFuncEqCommonDTO;
	}

	public void setRcmFuncEqCommonDTO(RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		this.rcmFuncEqCommonDTO = rcmFuncEqCommonDTO;
	}

	public RcmFuncEqItemListDTO getRcmFuncEqItemListDTO() {
		return rcmFuncEqItemListDTO;
	}

	public void setRcmFuncEqItemListDTO(RcmFuncEqItemListDTO rcmFuncEqItemListDTO) {
		this.rcmFuncEqItemListDTO = rcmFuncEqItemListDTO;
	}
}
