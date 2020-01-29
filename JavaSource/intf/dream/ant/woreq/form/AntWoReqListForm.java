package intf.dream.ant.woreq.form;

import common.struts.BaseForm;
import intf.dream.ant.woreq.dto.AntWoReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antWoReqListForm"
 */
public class AntWoReqListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntWoReqCommonDTO antWoReqCommonDTO = new AntWoReqCommonDTO();

	public AntWoReqCommonDTO getAntWoReqCommonDTO() {
		return antWoReqCommonDTO;
	}

	public void setAntWoReqCommonDTO(AntWoReqCommonDTO antWoReqCommonDTO) {
		this.antWoReqCommonDTO = antWoReqCommonDTO;
	}

    
}
