package intf.dream.android.online.finder.equip.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.equip.dto.AnOnFinderEquipCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderEquipListForm"
 */
public class AnOnFinderEquipListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderEquipCommonDTO anOnFinderEquipCommonDTO = new AnOnFinderEquipCommonDTO();

	public AnOnFinderEquipCommonDTO getAnOnFinderEquipCommonDTO() {
		return anOnFinderEquipCommonDTO;
	}

	public void setAnOnFinderEquipCommonDTO(AnOnFinderEquipCommonDTO anOnFinderEquipCommonDTO) {
		this.anOnFinderEquipCommonDTO = anOnFinderEquipCommonDTO;
	}

    
}
