package intf.dream.android.online.iss.form;

import common.struts.BaseForm;
import intf.dream.android.online.iss.dto.AnOnIssCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnIssListForm"
 */
public class AnOnIssListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnIssCommonDTO anOnIssCommonDTO = new AnOnIssCommonDTO();

	public AnOnIssCommonDTO getAnOnIssCommonDTO() {
		return anOnIssCommonDTO;
	}

	public void setAnOnIssCommonDTO(AnOnIssCommonDTO anOnIssCommonDTO) {
		this.anOnIssCommonDTO = anOnIssCommonDTO;
	}

    
}
