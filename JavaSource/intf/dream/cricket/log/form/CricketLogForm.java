package intf.dream.cricket.log.form;

import common.struts.BaseForm;
import intf.dream.cricket.log.dto.CricketLogCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketLogForm"
 */
public class CricketLogForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketLogCommonDTO cricketLogCommonDTO = new CricketLogCommonDTO();

	public CricketLogCommonDTO getCricketLogCommonDTO() {
		return cricketLogCommonDTO;
	}

	public void setCricketLogCommonDTO(CricketLogCommonDTO cricketLogCommonDTO) {
		this.cricketLogCommonDTO = cricketLogCommonDTO;
	}

    
}
