package intf.dream.android.offline.maptstktake.form;

import common.struts.BaseForm;
import intf.dream.android.offline.maptstktake.dto.AnIfPtstktakeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPtstktakeListForm"
 */
public class AnIfPtstktakeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPtstktakeCommonDTO anIfPtstktakeCommonDTO = new AnIfPtstktakeCommonDTO();

	public AnIfPtstktakeCommonDTO getAnIfPtstktakeCommonDTO() {
		return anIfPtstktakeCommonDTO;
	}

	public void setAnIfPtstktakeCommonDTO(AnIfPtstktakeCommonDTO anIfPtstktakeCommonDTO) {
		this.anIfPtstktakeCommonDTO = anIfPtstktakeCommonDTO;
	}

    
}
