package dream.rcm.ffail.form;

import common.struts.BaseForm;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 답변- 목록
 * @author  kim21017
 * @version $Id: RcmFfailItemListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFfailItemListForm"
 */
public class RcmFfailItemListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private RcmFfailCommonDTO rcmFfailCommonDTO = new RcmFfailCommonDTO();
    /** 답변  */
    private RcmFfailItemListDTO rcmFfailItemListDTO = new RcmFfailItemListDTO();
    
	public RcmFfailCommonDTO getRcmFfailCommonDTO() {
		return rcmFfailCommonDTO;
	}

	public void setRcmFfailCommonDTO(RcmFfailCommonDTO rcmFfailCommonDTO) {
		this.rcmFfailCommonDTO = rcmFfailCommonDTO;
	}

	public RcmFfailItemListDTO getRcmFfailItemListDTO() {
		return rcmFfailItemListDTO;
	}

	public void setRcmFfailItemListDTO(RcmFfailItemListDTO rcmFfailItemListDTO) {
		this.rcmFfailItemListDTO = rcmFfailItemListDTO;
	}
}
