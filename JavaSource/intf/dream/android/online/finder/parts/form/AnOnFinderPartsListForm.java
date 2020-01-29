package intf.dream.android.online.finder.parts.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.parts.dto.AnOnFinderPartsCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderPartsListForm"
 */
public class AnOnFinderPartsListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderPartsCommonDTO anOnFinderPartsCommonDTO = new AnOnFinderPartsCommonDTO();

	public AnOnFinderPartsCommonDTO getAnOnFinderPartsCommonDTO() {
		return anOnFinderPartsCommonDTO;
	}

	public void setAnOnFinderPartsCommonDTO(AnOnFinderPartsCommonDTO anOnFinderPartsCommonDTO) {
		this.anOnFinderPartsCommonDTO = anOnFinderPartsCommonDTO;
	}

    
}
