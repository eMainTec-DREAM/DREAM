package intf.dream.android.online.menu.form;

import common.struts.BaseForm;
import intf.dream.android.online.menu.dto.AnOnMenuCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnMenuListForm"
 */
public class AnOnMenuListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnMenuCommonDTO anOnMenuCommonDTO = new AnOnMenuCommonDTO();

	public AnOnMenuCommonDTO getAnOnMenuCommonDTO() {
		return anOnMenuCommonDTO;
	}

	public void setAnOnMenuCommonDTO(AnOnMenuCommonDTO anOnMenuCommonDTO) {
		this.anOnMenuCommonDTO = anOnMenuCommonDTO;
	}

    
}
