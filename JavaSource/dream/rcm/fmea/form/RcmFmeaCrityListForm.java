package dream.rcm.fmea.form;

import common.struts.BaseForm;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * 목록
 * @author  kim21017
 * @version $Id: RcmFmeaCrityListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFmeaCrityListForm"
 */
public class RcmFmeaCrityListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmFmeaCommonDTO rcmFmeaCommonDTO = new RcmFmeaCommonDTO();
    /** 답변  */
    private RcmFmeaCrityListDTO rcmFmeaCrityListDTO = new RcmFmeaCrityListDTO();
    
	public RcmFmeaCommonDTO getRcmFmeaCommonDTO() {
		return rcmFmeaCommonDTO;
	}

	public void setRcmFmeaCommonDTO(RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		this.rcmFmeaCommonDTO = rcmFmeaCommonDTO;
	}

	public RcmFmeaCrityListDTO getRcmFmeaCrityListDTO() {
		return rcmFmeaCrityListDTO;
	}

	public void setRcmFmeaCrityListDTO(RcmFmeaCrityListDTO rcmFmeaCrityListDTO) {
		this.rcmFmeaCrityListDTO = rcmFmeaCrityListDTO;
	}
}
