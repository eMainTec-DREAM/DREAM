package intf.dream.bee.inspection.form;

import common.struts.BaseForm;
import intf.dream.bee.inspection.dto.BeeInspectionCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeInspectionListForm"
 */
public class BeeInspectionListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeInspectionCommonDTO beeInspectionCommonDTO = new BeeInspectionCommonDTO();

	public BeeInspectionCommonDTO getBeeInspectionCommonDTO() {
		return beeInspectionCommonDTO;
	}

	public void setBeeInspectionCommonDTO(BeeInspectionCommonDTO beeInspectionCommonDTO) {
		this.beeInspectionCommonDTO = beeInspectionCommonDTO;
	}

    
}
