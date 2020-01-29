package intf.dream.cricket.check.form;

import common.struts.BaseForm;
import intf.dream.cricket.check.dto.CricketCheckCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketCheckForm"
 */
public class CricketCheckForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketCheckCommonDTO cricketCheckCommonDTO = new CricketCheckCommonDTO();

	public CricketCheckCommonDTO getCricketCheckCommonDTO() {
		return cricketCheckCommonDTO;
	}

	public void setCricketCheckCommonDTO(CricketCheckCommonDTO cricketCheckCommonDTO) {
		this.cricketCheckCommonDTO = cricketCheckCommonDTO;
	}

    
}
