package intf.dream.bee.unplan.form;

import common.struts.BaseForm;
import intf.dream.bee.unplan.dto.BeeUnPlanCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeUnPlanListForm"
 */
public class BeeUnPlanListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeUnPlanCommonDTO beeUnPlanCommonDTO = new BeeUnPlanCommonDTO();

	public BeeUnPlanCommonDTO getBeeUnPlanCommonDTO() {
		return beeUnPlanCommonDTO;
	}

	public void setBeeUnPlanCommonDTO(BeeUnPlanCommonDTO beeUnPlanCommonDTO) {
		this.beeUnPlanCommonDTO = beeUnPlanCommonDTO;
	}

    
}
