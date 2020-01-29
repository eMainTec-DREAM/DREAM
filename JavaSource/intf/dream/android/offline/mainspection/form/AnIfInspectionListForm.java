package intf.dream.android.offline.mainspection.form;

import common.struts.BaseForm;
import intf.dream.android.offline.mainspection.dto.AnIfInspectionCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfInspectionListForm"
 */
public class AnIfInspectionListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfInspectionCommonDTO anIfInspectionCommonDTO = new AnIfInspectionCommonDTO();

	public AnIfInspectionCommonDTO getAnIfInspectionCommonDTO() {
		return anIfInspectionCommonDTO;
	}

	public void setAnIfInspectionCommonDTO(AnIfInspectionCommonDTO anIfInspectionCommonDTO) {
		this.anIfInspectionCommonDTO = anIfInspectionCommonDTO;
	}

    
}
