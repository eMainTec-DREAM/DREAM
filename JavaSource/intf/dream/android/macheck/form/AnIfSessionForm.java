package intf.dream.android.macheck.form;

import common.struts.BaseForm;
import intf.dream.android.macheck.dto.AnIfCheckCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfSessionForm"
 */
public class AnIfSessionForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfCheckCommonDTO anIfCheckCommonDTO = new AnIfCheckCommonDTO();

	public AnIfCheckCommonDTO getAnIfCheckCommonDTO() {
		return anIfCheckCommonDTO;
	}

	public void setAnIfCheckCommonDTO(AnIfCheckCommonDTO anIfCheckCommonDTO) {
		this.anIfCheckCommonDTO = anIfCheckCommonDTO;
	}

    
}
