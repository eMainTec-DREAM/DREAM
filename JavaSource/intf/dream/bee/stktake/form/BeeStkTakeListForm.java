package intf.dream.bee.stktake.form;

import common.struts.BaseForm;
import intf.dream.bee.stktake.dto.BeeStkTakeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeStkTakeListForm"
 */
public class BeeStkTakeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeStkTakeCommonDTO beeStkTakeCommonDTO = new BeeStkTakeCommonDTO();

	public BeeStkTakeCommonDTO getBeeStkTakeCommonDTO() {
		return beeStkTakeCommonDTO;
	}

	public void setBeeStkTakeCommonDTO(BeeStkTakeCommonDTO beeStkTakeCommonDTO) {
		this.beeStkTakeCommonDTO = beeStkTakeCommonDTO;
	}

    
}
