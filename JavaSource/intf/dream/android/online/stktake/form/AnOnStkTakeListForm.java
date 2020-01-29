package intf.dream.android.online.stktake.form;

import common.struts.BaseForm;
import intf.dream.android.online.stktake.dto.AnOnStkTakeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnStkTakeListForm"
 */
public class AnOnStkTakeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnStkTakeCommonDTO anOnStkTakeCommonDTO = new AnOnStkTakeCommonDTO();

	public AnOnStkTakeCommonDTO getAnOnStkTakeCommonDTO() {
		return anOnStkTakeCommonDTO;
	}

	public void setAnOnStkTakeCommonDTO(AnOnStkTakeCommonDTO anOnStkTakeCommonDTO) {
		this.anOnStkTakeCommonDTO = anOnStkTakeCommonDTO;
	}

    
}
