package intf.dream.android.online.auth.form;

import common.struts.BaseForm;
import intf.dream.android.online.auth.dto.AnOnAuthCommonDTO;

/**
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnAuthListForm"
 */
public class AnOnAuthListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnAuthCommonDTO anOnAuthCommonDTO = new AnOnAuthCommonDTO();

	public AnOnAuthCommonDTO getAnOnAuthCommonDTO() {
		return anOnAuthCommonDTO;
	}

	public void setAnOnAuthCommonDTO(AnOnAuthCommonDTO anOnAuthCommonDTO) {
		this.anOnAuthCommonDTO = anOnAuthCommonDTO;
	}

    
}
