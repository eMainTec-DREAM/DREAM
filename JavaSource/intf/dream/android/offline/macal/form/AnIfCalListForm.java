package intf.dream.android.offline.macal.form;

import common.struts.BaseForm;
import intf.dream.android.offline.macal.dto.AnIfCalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfCalListForm"
 */
public class AnIfCalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfCalCommonDTO anIfCalCommonDTO = new AnIfCalCommonDTO();

	public AnIfCalCommonDTO getAnIfCalCommonDTO() {
		return anIfCalCommonDTO;
	}

	public void setAnIfCalCommonDTO(AnIfCalCommonDTO anIfCalCommonDTO) {
		this.anIfCalCommonDTO = anIfCalCommonDTO;
	}

    
}
