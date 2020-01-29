package intf.dream.android.offline.mainitcode.form;

import common.struts.BaseForm;
import intf.dream.android.offline.mainitcode.dto.AnIfInitcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfInitcodeListForm"
 */
public class AnIfInitcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfInitcodeCommonDTO anIfInitcodeCommonDTO = new AnIfInitcodeCommonDTO();

	public AnIfInitcodeCommonDTO getAnIfInitcodeCommonDTO() {
		return anIfInitcodeCommonDTO;
	}

	public void setAnIfInitcodeCommonDTO(AnIfInitcodeCommonDTO anIfInitcodeCommonDTO) {
		this.anIfInitcodeCommonDTO = anIfInitcodeCommonDTO;
	}

    
}
