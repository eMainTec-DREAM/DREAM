package intf.dream.cricket.finder.plant.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.plant.dto.CricketFinderPlantCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="cricketFinderPlantListForm"
 */
public class CricketFinderPlantListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderPlantCommonDTO cricketFinderPlantCommonDTO = new CricketFinderPlantCommonDTO();

	public CricketFinderPlantCommonDTO getCricketFinderPlantCommonDTO() {
		return cricketFinderPlantCommonDTO;
	}

	public void setCricketFinderPlantCommonDTO(CricketFinderPlantCommonDTO cricketFinderPlantCommonDTO) {
		this.cricketFinderPlantCommonDTO = cricketFinderPlantCommonDTO;
	}

    
}
