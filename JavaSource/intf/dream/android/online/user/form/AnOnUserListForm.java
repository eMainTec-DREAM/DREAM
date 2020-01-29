package intf.dream.android.online.user.form;

import common.struts.BaseForm;
import intf.dream.android.online.user.dto.AnOnUserCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnUserListForm"
 */
public class AnOnUserListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnUserCommonDTO anOnUserCommonDTO = new AnOnUserCommonDTO();

	public AnOnUserCommonDTO getAnOnUserCommonDTO() {
		return anOnUserCommonDTO;
	}

	public void setAnOnUserCommonDTO(AnOnUserCommonDTO anOnUserCommonDTO) {
		this.anOnUserCommonDTO = anOnUserCommonDTO;
	}

    
}
