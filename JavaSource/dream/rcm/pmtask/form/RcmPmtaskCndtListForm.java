package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;

/**
 * 목록
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskCndtListForm"
 */
public class RcmPmtaskCndtListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
    /** 답변  */
    private RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO = new RcmPmtaskCndtListDTO();
    
	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}

	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}

	public RcmPmtaskCndtListDTO getRcmPmtaskCndtListDTO() {
		return rcmPmtaskCndtListDTO;
	}

	public void setRcmPmtaskCndtListDTO(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO) {
		this.rcmPmtaskCndtListDTO = rcmPmtaskCndtListDTO;
	}
}
