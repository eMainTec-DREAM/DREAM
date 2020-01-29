package intf.dream.android.offline.maunplanwork.form;

import common.struts.BaseForm;
import intf.dream.android.offline.maunplanwork.dto.AnIfUnplanworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfUnplanworkListForm"
 */
public class AnIfUnplanworkListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfUnplanworkCommonDTO anIfUnplanworkCommonDTO = new AnIfUnplanworkCommonDTO();

	public AnIfUnplanworkCommonDTO getAnIfUnplanworkCommonDTO() {
		return anIfUnplanworkCommonDTO;
	}

	public void setAnIfUnplanworkCommonDTO(AnIfUnplanworkCommonDTO anIfUnplanworkCommonDTO) {
		this.anIfUnplanworkCommonDTO = anIfUnplanworkCommonDTO;
	}

    
}
