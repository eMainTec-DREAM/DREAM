package intf.dream.android.online.pmi.part.form;

import common.struts.BaseForm;
import intf.dream.android.online.pmi.part.dto.AnOnPmiPartCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnPmiPartListForm"
 */
public class AnOnPmiPartListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnPmiPartCommonDTO anOnPmiPartCommonDTO = new AnOnPmiPartCommonDTO();

	public AnOnPmiPartCommonDTO getAnOnPmiPartCommonDTO() {
		return anOnPmiPartCommonDTO;
	}

	public void setAnOnPmiPartCommonDTO(AnOnPmiPartCommonDTO anOnPmiPartCommonDTO) {
		this.anOnPmiPartCommonDTO = anOnPmiPartCommonDTO;
	}

}
