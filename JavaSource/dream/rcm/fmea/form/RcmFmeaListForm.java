package dream.rcm.fmea.form;

import common.struts.BaseForm;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;

/**
 * 목록 form
 * @author  kim21017
 * @version $Id: RcmFmeaListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFmeaListForm"
 */
public class RcmFmeaListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmFmeaCommonDTO rcmFmeaCommonDTO = new RcmFmeaCommonDTO();
    
	public RcmFmeaCommonDTO getRcmFmeaCommonDTO() {
		return rcmFmeaCommonDTO;
	}

	public void setRcmFmeaCommonDTO(RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		this.rcmFmeaCommonDTO = rcmFmeaCommonDTO;
	}
}
