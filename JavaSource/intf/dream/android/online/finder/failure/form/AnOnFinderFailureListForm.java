package intf.dream.android.online.finder.failure.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.failure.dto.AnOnFinderFailureCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderFailureListForm"
 */
public class AnOnFinderFailureListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderFailureCommonDTO anOnFinderFailureCommonDTO = new AnOnFinderFailureCommonDTO();

	public AnOnFinderFailureCommonDTO getAnOnFinderFailureCommonDTO() {
		return anOnFinderFailureCommonDTO;
	}

	public void setAnOnFinderFailureCommonDTO(AnOnFinderFailureCommonDTO anOnFinderFailureCommonDTO) {
		this.anOnFinderFailureCommonDTO = anOnFinderFailureCommonDTO;
	}

    
}
