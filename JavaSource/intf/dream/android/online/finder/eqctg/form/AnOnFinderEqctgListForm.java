package intf.dream.android.online.finder.eqctg.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.eqctg.dto.AnOnFinderEqctgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderEqctgListForm"
 */
public class AnOnFinderEqctgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderEqctgCommonDTO anOnFinderEqctgCommonDTO = new AnOnFinderEqctgCommonDTO();

	public AnOnFinderEqctgCommonDTO getAnOnFinderEqctgCommonDTO() {
		return anOnFinderEqctgCommonDTO;
	}

	public void setAnOnFinderEqctgCommonDTO(AnOnFinderEqctgCommonDTO anOnFinderEqctgCommonDTO) {
		this.anOnFinderEqctgCommonDTO = anOnFinderEqctgCommonDTO;
	}

    
}
