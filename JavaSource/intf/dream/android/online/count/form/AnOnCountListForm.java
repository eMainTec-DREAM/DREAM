package intf.dream.android.online.count.form;

import common.struts.BaseForm;
import intf.dream.android.online.count.dto.AnOnCountCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnCountListForm"
 */
public class AnOnCountListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnCountCommonDTO anOnCountCommonDTO = new AnOnCountCommonDTO();

	public AnOnCountCommonDTO getAnOnCountCommonDTO() {
		return anOnCountCommonDTO;
	}

	public void setAnOnCountCommonDTO(AnOnCountCommonDTO anOnCountCommonDTO) {
		this.anOnCountCommonDTO = anOnCountCommonDTO;
	}

    
}
