package intf.dream.android.offline.maptiss.form;

import common.struts.BaseForm;
import intf.dream.android.offline.maptiss.dto.AnIfPtissCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPtissListForm"
 */
public class AnIfPtissListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPtissCommonDTO anIfPtissCommonDTO = new AnIfPtissCommonDTO();

	public AnIfPtissCommonDTO getAnIfPtissCommonDTO() {
		return anIfPtissCommonDTO;
	}

	public void setAnIfPtissCommonDTO(AnIfPtissCommonDTO anIfPtissCommonDTO) {
		this.anIfPtissCommonDTO = anIfPtissCommonDTO;
	}

    
}
