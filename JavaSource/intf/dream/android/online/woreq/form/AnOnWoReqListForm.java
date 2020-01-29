package intf.dream.android.online.woreq.form;

import common.file.FileForm;
import intf.dream.android.online.woreq.dto.AnOnWoReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnWoReqListForm"
 */
public class AnOnWoReqListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnWoReqCommonDTO anOnWoReqCommonDTO = new AnOnWoReqCommonDTO();

	public AnOnWoReqCommonDTO getAnOnWoReqCommonDTO() {
		return anOnWoReqCommonDTO;
	}

	public void setAnOnWoReqCommonDTO(AnOnWoReqCommonDTO anOnWoReqCommonDTO) {
		this.anOnWoReqCommonDTO = anOnWoReqCommonDTO;
	}

    
}
