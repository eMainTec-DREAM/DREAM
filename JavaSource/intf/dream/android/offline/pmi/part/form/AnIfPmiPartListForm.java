package intf.dream.android.offline.pmi.part.form;

import common.struts.BaseForm;
import intf.dream.android.offline.pmi.part.dto.AnIfPmiPartCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPmiPartListForm"
 */
public class AnIfPmiPartListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPmiPartCommonDTO anIfPmiPartCommonDTO = new AnIfPmiPartCommonDTO();

	public AnIfPmiPartCommonDTO getAnIfPmiPartCommonDTO() {
		return anIfPmiPartCommonDTO;
	}

	public void setAnIfPmiPartCommonDTO(AnIfPmiPartCommonDTO anIfPmiPartCommonDTO) {
		this.anIfPmiPartCommonDTO = anIfPmiPartCommonDTO;
	}

    
}
