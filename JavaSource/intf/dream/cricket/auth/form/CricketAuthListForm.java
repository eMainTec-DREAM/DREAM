package intf.dream.cricket.auth.form;

import common.struts.BaseForm;
import intf.dream.cricket.auth.dto.CricketAuthCommonDTO;

/**
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketAuthListForm"
 */
public class CricketAuthListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketAuthCommonDTO cricketAuthCommonDTO = new CricketAuthCommonDTO();

	public CricketAuthCommonDTO getCricketAuthCommonDTO() {
		return cricketAuthCommonDTO;
	}

	public void setCricketAuthCommonDTO(CricketAuthCommonDTO cricketAuthCommonDTO) {
		this.cricketAuthCommonDTO = cricketAuthCommonDTO;
	}

    
}
