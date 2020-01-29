package dream.rcm.taskmap.form;

import common.struts.BaseForm;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 질의 - 목록 form
 * @author  kim21017
 * @version $Id: RcmTaskMapListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmTaskMapListForm"
 */
public class RcmTaskMapListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private RcmTaskMapCommonDTO rcmTaskMapCommonDTO = new RcmTaskMapCommonDTO();
    
	public RcmTaskMapCommonDTO getRcmTaskMapCommonDTO() {
		return rcmTaskMapCommonDTO;
	}

	public void setRcmTaskMapCommonDTO(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) {
		this.rcmTaskMapCommonDTO = rcmTaskMapCommonDTO;
	}
}
