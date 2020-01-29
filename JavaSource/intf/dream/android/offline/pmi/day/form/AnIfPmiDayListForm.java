package intf.dream.android.offline.pmi.day.form;

import common.struts.BaseForm;
import intf.dream.android.offline.pmi.day.dto.AnIfPmiDayCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPmiDayListForm"
 */
public class AnIfPmiDayListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPmiDayCommonDTO anIfPmiDayCommonDTO = new AnIfPmiDayCommonDTO();

	public AnIfPmiDayCommonDTO getAnIfPmiDayCommonDTO() {
		return anIfPmiDayCommonDTO;
	}

	public void setAnIfPmiDayCommonDTO(AnIfPmiDayCommonDTO anIfPmiDayCommonDTO) {
		this.anIfPmiDayCommonDTO = anIfPmiDayCommonDTO;
	}

    
}
