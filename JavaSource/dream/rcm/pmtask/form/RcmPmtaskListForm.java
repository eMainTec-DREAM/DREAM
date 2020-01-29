package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;

/**
 * 목록 form
 * @author  kim21017
 * @version $Id: RcmPmtaskListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskListForm"
 */
public class RcmPmtaskListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
    
	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}

	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}
}
