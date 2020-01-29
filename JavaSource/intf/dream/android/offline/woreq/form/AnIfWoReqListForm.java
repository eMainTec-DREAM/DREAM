package intf.dream.android.offline.woreq.form;

import common.struts.BaseForm;
import intf.dream.android.offline.woreq.dto.AnIfWoReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfWoReqListForm"
 */
public class AnIfWoReqListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfWoReqCommonDTO anIfWoReqCommonDTO = new AnIfWoReqCommonDTO();

	public AnIfWoReqCommonDTO getAnIfWoReqCommonDTO() {
		return anIfWoReqCommonDTO;
	}

	public void setAnIfWoReqCommonDTO(AnIfWoReqCommonDTO anIfWoReqCommonDTO) {
		this.anIfWoReqCommonDTO = anIfWoReqCommonDTO;
	}

    
}
