package dream.rcm.ffail.form;

import common.struts.BaseForm;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * ���� - ��� form
 * @author  kim21017
 * @version $Id: RcmFfailListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFfailListForm"
 */
public class RcmFfailListForm extends BaseForm
{    
    //===============================================================
    /** ���� ���� */
    private RcmFfailCommonDTO rcmFfailCommonDTO = new RcmFfailCommonDTO();
    
	public RcmFfailCommonDTO getRcmFfailCommonDTO() {
		return rcmFfailCommonDTO;
	}

	public void setRcmFfailCommonDTO(RcmFfailCommonDTO rcmFfailCommonDTO) {
		this.rcmFfailCommonDTO = rcmFfailCommonDTO;
	}
}
