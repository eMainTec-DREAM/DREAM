package intf.dream.bee.finder.equip.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.equip.dto.BeeFinderEquipCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderEquipListForm"
 */
public class BeeFinderEquipListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderEquipCommonDTO beeFinderEquipCommonDTO = new BeeFinderEquipCommonDTO();

	public BeeFinderEquipCommonDTO getBeeFinderEquipCommonDTO() {
		return beeFinderEquipCommonDTO;
	}

	public void setBeeFinderEquipCommonDTO(BeeFinderEquipCommonDTO beeFinderEquipCommonDTO) {
		this.beeFinderEquipCommonDTO = beeFinderEquipCommonDTO;
	}

    
}
