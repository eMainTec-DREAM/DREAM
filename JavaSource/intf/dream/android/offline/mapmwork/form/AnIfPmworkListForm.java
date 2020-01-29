package intf.dream.android.offline.mapmwork.form;

import common.struts.BaseForm;
import intf.dream.android.offline.mapmwork.dto.AnIfPmworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPmworkListForm"
 */
public class AnIfPmworkListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPmworkCommonDTO anIfPmworkCommonDTO = new AnIfPmworkCommonDTO();

	public AnIfPmworkCommonDTO getAnIfPmworkCommonDTO() {
		return anIfPmworkCommonDTO;
	}

	public void setAnIfPmworkCommonDTO(AnIfPmworkCommonDTO anIfPmworkCommonDTO) {
		this.anIfPmworkCommonDTO = anIfPmworkCommonDTO;
	}

    
}
