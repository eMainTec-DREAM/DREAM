package intf.dream.android.malog.form;

import common.struts.BaseForm;
import intf.dream.android.malog.dto.AnIfLogCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfLogForm"
 */
public class AnIfLogForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfLogCommonDTO anIfLogCommonDTO = new AnIfLogCommonDTO();

	public AnIfLogCommonDTO getAnIfLogCommonDTO() {
		return anIfLogCommonDTO;
	}

	public void setAnIfLogCommonDTO(AnIfLogCommonDTO anIfLogCommonDTO) {
		this.anIfLogCommonDTO = anIfLogCommonDTO;
	}

    
}
