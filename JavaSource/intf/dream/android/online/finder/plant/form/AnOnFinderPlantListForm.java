package intf.dream.android.online.finder.plant.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.plant.dto.AnOnFinderPlantCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="anOnFinderPlantListForm"
 */
public class AnOnFinderPlantListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderPlantCommonDTO anOnFinderPlantCommonDTO = new AnOnFinderPlantCommonDTO();

	public AnOnFinderPlantCommonDTO getAnOnFinderPlantCommonDTO() {
		return anOnFinderPlantCommonDTO;
	}

	public void setAnOnFinderPlantCommonDTO(AnOnFinderPlantCommonDTO anOnFinderPlantCommonDTO) {
		this.anOnFinderPlantCommonDTO = anOnFinderPlantCommonDTO;
	}

    
}
