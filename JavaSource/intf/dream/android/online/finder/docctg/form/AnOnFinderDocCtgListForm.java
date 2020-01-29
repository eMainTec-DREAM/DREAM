package intf.dream.android.online.finder.docctg.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.docctg.dto.AnOnFinderDocCtgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderDocCtgListForm"
 */
public class AnOnFinderDocCtgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderDocCtgCommonDTO anOnFinderDocCtgCommonDTO = new AnOnFinderDocCtgCommonDTO();

	public AnOnFinderDocCtgCommonDTO getAnOnFinderDocCtgCommonDTO() {
		return anOnFinderDocCtgCommonDTO;
	}

	public void setAnOnFinderDocCtgCommonDTO(AnOnFinderDocCtgCommonDTO anOnFinderDocCtgCommonDTO) {
		this.anOnFinderDocCtgCommonDTO = anOnFinderDocCtgCommonDTO;
	}

    
}
