package intf.dream.cricket.menu.form;

import common.struts.BaseForm;
import intf.dream.cricket.menu.dto.CricketMenuCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketMenuListForm"
 */
public class CricketMenuListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketMenuCommonDTO cricketMenuCommonDTO = new CricketMenuCommonDTO();

	public CricketMenuCommonDTO getCricketMenuCommonDTO() {
		return cricketMenuCommonDTO;
	}

	public void setCricketMenuCommonDTO(CricketMenuCommonDTO cricketMenuCommonDTO) {
		this.cricketMenuCommonDTO = cricketMenuCommonDTO;
	}

    
}
