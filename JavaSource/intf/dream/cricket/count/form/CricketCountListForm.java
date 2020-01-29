package intf.dream.cricket.count.form;

import common.struts.BaseForm;
import intf.dream.cricket.count.dto.CricketCountCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketCountListForm"
 */
public class CricketCountListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketCountCommonDTO cricketCountCommonDTO = new CricketCountCommonDTO();

	public CricketCountCommonDTO getCricketCountCommonDTO() {
		return cricketCountCommonDTO;
	}

	public void setCricketCountCommonDTO(CricketCountCommonDTO cricketCountCommonDTO) {
		this.cricketCountCommonDTO = cricketCountCommonDTO;
	}

    
}
