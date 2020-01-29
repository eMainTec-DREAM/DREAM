package intf.dream.cricket.finder.equip.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.equip.dto.CricketFinderEquipCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderEquipListForm"
 */
public class CricketFinderEquipListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderEquipCommonDTO cricketFinderEquipCommonDTO = new CricketFinderEquipCommonDTO();

	public CricketFinderEquipCommonDTO getCricketFinderEquipCommonDTO() {
		return cricketFinderEquipCommonDTO;
	}

	public void setCricketFinderEquipCommonDTO(CricketFinderEquipCommonDTO cricketFinderEquipCommonDTO) {
		this.cricketFinderEquipCommonDTO = cricketFinderEquipCommonDTO;
	}

    
}
