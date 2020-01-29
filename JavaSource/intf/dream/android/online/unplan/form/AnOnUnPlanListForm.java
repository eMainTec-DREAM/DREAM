package intf.dream.android.online.unplan.form;

import common.struts.BaseForm;
import intf.dream.android.online.unplan.dto.AnOnUnPlanCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnUnPlanListForm"
 */
public class AnOnUnPlanListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnUnPlanCommonDTO anOnUnPlanCommonDTO = new AnOnUnPlanCommonDTO();

	public AnOnUnPlanCommonDTO getAnOnUnPlanCommonDTO() {
		return anOnUnPlanCommonDTO;
	}

	public void setAnOnUnPlanCommonDTO(AnOnUnPlanCommonDTO anOnUnPlanCommonDTO) {
		this.anOnUnPlanCommonDTO = anOnUnPlanCommonDTO;
	}

    
}
