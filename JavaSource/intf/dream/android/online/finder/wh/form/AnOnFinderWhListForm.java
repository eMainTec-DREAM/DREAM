package intf.dream.android.online.finder.wh.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.wh.dto.AnOnFinderWhCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="anOnFinderWhListForm"
 */
public class AnOnFinderWhListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderWhCommonDTO anOnFinderWhCommonDTO = new AnOnFinderWhCommonDTO();

	public AnOnFinderWhCommonDTO getAnOnFinderWhCommonDTO() {
		return anOnFinderWhCommonDTO;
	}

	public void setAnOnFinderWhCommonDTO(AnOnFinderWhCommonDTO anOnFinderWhCommonDTO) {
		this.anOnFinderWhCommonDTO = anOnFinderWhCommonDTO;
	}

    
}
