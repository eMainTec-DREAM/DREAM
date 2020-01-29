package intf.dream.android.online.inspection.form;

import common.struts.BaseForm;
import intf.dream.android.online.inspection.dto.AnOnInspectionCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnInspectionListForm"
 */
public class AnOnInspectionListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnInspectionCommonDTO anOnInspectionCommonDTO = new AnOnInspectionCommonDTO();

	public AnOnInspectionCommonDTO getAnOnInspectionCommonDTO() {
		return anOnInspectionCommonDTO;
	}

	public void setAnOnInspectionCommonDTO(AnOnInspectionCommonDTO anOnInspectionCommonDTO) {
		this.anOnInspectionCommonDTO = anOnInspectionCommonDTO;
	}

    
}
