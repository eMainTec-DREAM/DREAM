package intf.dream.android.online.pmi.day.form;

import common.struts.BaseForm;
import intf.dream.android.online.pmi.day.dto.AnOnPmiDayCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnPmiDayListForm"
 */
public class AnOnPmiDayListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnPmiDayCommonDTO anOnPmiDayCommonDTO = new AnOnPmiDayCommonDTO();

	public AnOnPmiDayCommonDTO getAnOnPmiDayCommonDTO() {
		return anOnPmiDayCommonDTO;
	}

	public void setAnOnPmiDayCommonDTO(AnOnPmiDayCommonDTO anOnPmiDayCommonDTO) {
		this.anOnPmiDayCommonDTO = anOnPmiDayCommonDTO;
	}

    
}
