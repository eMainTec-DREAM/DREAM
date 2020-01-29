package intf.dream.android.online.maptpurreq.form;

import common.struts.BaseForm;
import intf.dream.android.online.maptpurreq.dto.AnOnMaPtPurReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnMaPtPurReqListForm"
 */
public class AnOnMaPtPurReqListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnMaPtPurReqCommonDTO anOnMaPtPurReqCommonDTO = new AnOnMaPtPurReqCommonDTO();

	public AnOnMaPtPurReqCommonDTO getAnOnMaPtPurReqCommonDTO() {
		return anOnMaPtPurReqCommonDTO;
	}

	public void setAnOnMaPtPurReqCommonDTO(AnOnMaPtPurReqCommonDTO anOnMaPtPurReqCommonDTO) {
		this.anOnMaPtPurReqCommonDTO = anOnMaPtPurReqCommonDTO;
	}

    
}
