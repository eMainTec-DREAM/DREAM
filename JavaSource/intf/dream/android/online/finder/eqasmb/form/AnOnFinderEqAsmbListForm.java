package intf.dream.android.online.finder.eqasmb.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.eqasmb.dto.AnOnFinderEqAsmbCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderEqAsmbListForm"
 */
public class AnOnFinderEqAsmbListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderEqAsmbCommonDTO anOnFinderEqAsmbCommonDTO = new AnOnFinderEqAsmbCommonDTO();

	public AnOnFinderEqAsmbCommonDTO getAnOnFinderEqAsmbCommonDTO() {
		return anOnFinderEqAsmbCommonDTO;
	}

	public void setAnOnFinderEqAsmbCommonDTO(AnOnFinderEqAsmbCommonDTO anOnFinderEqAsmbCommonDTO) {
		this.anOnFinderEqAsmbCommonDTO = anOnFinderEqAsmbCommonDTO;
	}

    
}
