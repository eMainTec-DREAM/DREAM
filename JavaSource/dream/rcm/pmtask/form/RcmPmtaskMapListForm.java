package dream.rcm.pmtask.form;

import common.struts.BaseForm;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * ���
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmPmtaskMapListForm"
 */
public class RcmPmtaskMapListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private RcmPmtaskCommonDTO rcmPmtaskCommonDTO = new RcmPmtaskCommonDTO();
    /** �亯  */
    private RcmPmtaskMapListDTO rcmPmtaskMapListDTO = new RcmPmtaskMapListDTO();
    
	public RcmPmtaskCommonDTO getRcmPmtaskCommonDTO() {
		return rcmPmtaskCommonDTO;
	}

	public void setRcmPmtaskCommonDTO(RcmPmtaskCommonDTO rcmPmtaskCommonDTO) {
		this.rcmPmtaskCommonDTO = rcmPmtaskCommonDTO;
	}

	public RcmPmtaskMapListDTO getRcmPmtaskMapListDTO() {
		return rcmPmtaskMapListDTO;
	}

	public void setRcmPmtaskMapListDTO(RcmPmtaskMapListDTO rcmPmtaskMapListDTO) {
		this.rcmPmtaskMapListDTO = rcmPmtaskMapListDTO;
	}
}
