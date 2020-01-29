package intf.dream.android.offline.maptstock.form;

import common.struts.BaseForm;
import intf.dream.android.offline.maptstock.dto.AnIfPtstockCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPtstockListForm"
 */
public class AnIfPtstockListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPtstockCommonDTO anIfPtstockCommonDTO = new AnIfPtstockCommonDTO();

	public AnIfPtstockCommonDTO getAnIfPtstockCommonDTO() {
		return anIfPtstockCommonDTO;
	}

	public void setAnIfPtstockCommonDTO(AnIfPtstockCommonDTO anIfPtstockCommonDTO) {
		this.anIfPtstockCommonDTO = anIfPtstockCommonDTO;
	}

    
}
