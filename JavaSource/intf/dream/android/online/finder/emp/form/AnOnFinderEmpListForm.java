package intf.dream.android.online.finder.emp.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.emp.dto.AnOnFinderEmpCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderEmpListForm"
 */
public class AnOnFinderEmpListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderEmpCommonDTO anOnFinderEmpCommonDTO = new AnOnFinderEmpCommonDTO();

	public AnOnFinderEmpCommonDTO getAnOnFinderEmpCommonDTO() {
		return anOnFinderEmpCommonDTO;
	}

	public void setAnOnFinderEmpCommonDTO(AnOnFinderEmpCommonDTO anOnFinderEmpCommonDTO) {
		this.anOnFinderEmpCommonDTO = anOnFinderEmpCommonDTO;
	}

    
}
