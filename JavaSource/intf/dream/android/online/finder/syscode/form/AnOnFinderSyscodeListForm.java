package intf.dream.android.online.finder.syscode.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.syscode.dto.AnOnFinderSyscodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderSyscodeListForm"
 */
public class AnOnFinderSyscodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderSyscodeCommonDTO anOnFinderSyscodeCommonDTO = new AnOnFinderSyscodeCommonDTO();

	public AnOnFinderSyscodeCommonDTO getAnOnFinderSyscodeCommonDTO() {
		return anOnFinderSyscodeCommonDTO;
	}

	public void setAnOnFinderSyscodeCommonDTO(AnOnFinderSyscodeCommonDTO anOnFinderSyscodeCommonDTO) {
		this.anOnFinderSyscodeCommonDTO = anOnFinderSyscodeCommonDTO;
	}

    
}
