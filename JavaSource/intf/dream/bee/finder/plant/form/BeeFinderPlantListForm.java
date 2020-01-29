package intf.dream.bee.finder.plant.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.plant.dto.BeeFinderPlantCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="beeFinderPlantListForm"
 */
public class BeeFinderPlantListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderPlantCommonDTO beeFinderPlantCommonDTO = new BeeFinderPlantCommonDTO();

	public BeeFinderPlantCommonDTO getBeeFinderPlantCommonDTO() {
		return beeFinderPlantCommonDTO;
	}

	public void setBeeFinderPlantCommonDTO(BeeFinderPlantCommonDTO beeFinderPlantCommonDTO) {
		this.beeFinderPlantCommonDTO = beeFinderPlantCommonDTO;
	}

    
}
