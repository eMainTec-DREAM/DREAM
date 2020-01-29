package intf.dream.android.online.common.form;

import common.struts.BaseForm;
import intf.dream.android.online.common.dto.AnOnCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnCommonListForm"
 */
public class AnOnCommonListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnCommonDTO anOnCommonDTO = new AnOnCommonDTO();

	public AnOnCommonDTO getAnOnCommonDTO() {
		return anOnCommonDTO;
	}

	public void setAnOnCommonDTO(AnOnCommonDTO anOnCommonDTO) {
		this.anOnCommonDTO = anOnCommonDTO;
	}
}
