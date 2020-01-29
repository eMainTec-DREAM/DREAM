package intf.dream.android.online.finder.eqloc.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.eqloc.dto.AnOnFinderEqlocCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderEqlocListForm"
 */
public class AnOnFinderEqlocListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderEqlocCommonDTO anOnFinderEqlocCommonDTO = new AnOnFinderEqlocCommonDTO();

	public AnOnFinderEqlocCommonDTO getAnOnFinderEqlocCommonDTO() {
		return anOnFinderEqlocCommonDTO;
	}

	public void setAnOnFinderEqlocCommonDTO(AnOnFinderEqlocCommonDTO anOnFinderEqlocCommonDTO) {
		this.anOnFinderEqlocCommonDTO = anOnFinderEqlocCommonDTO;
	}

    
}
