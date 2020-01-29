package intf.dream.android.online.cal.form;

import common.struts.BaseForm;
import intf.dream.android.online.cal.dto.AnOnCalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnCalListForm"
 */
public class AnOnCalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnCalCommonDTO anOnCalCommonDTO = new AnOnCalCommonDTO();

	public AnOnCalCommonDTO getAnOnCalCommonDTO() {
		return anOnCalCommonDTO;
	}

	public void setAnOnCalCommonDTO(AnOnCalCommonDTO anOnCalCommonDTO) {
		this.anOnCalCommonDTO = anOnCalCommonDTO;
	}

    
}
