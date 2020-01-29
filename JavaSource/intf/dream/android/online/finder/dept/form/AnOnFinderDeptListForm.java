package intf.dream.android.online.finder.dept.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.dept.dto.AnOnFinderDeptCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderDeptListForm"
 */
public class AnOnFinderDeptListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderDeptCommonDTO anOnFinderDeptCommonDTO = new AnOnFinderDeptCommonDTO();

	public AnOnFinderDeptCommonDTO getAnOnFinderDeptCommonDTO() {
		return anOnFinderDeptCommonDTO;
	}

	public void setAnOnFinderDeptCommonDTO(AnOnFinderDeptCommonDTO anOnFinderDeptCommonDTO) {
		this.anOnFinderDeptCommonDTO = anOnFinderDeptCommonDTO;
	}

    
}
