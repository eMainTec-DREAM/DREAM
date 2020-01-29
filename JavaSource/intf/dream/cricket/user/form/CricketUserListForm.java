package intf.dream.cricket.user.form;

import common.struts.BaseForm;
import intf.dream.cricket.user.dto.CricketUserCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketUserListForm"
 */
public class CricketUserListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketUserCommonDTO cricketUserCommonDTO = new CricketUserCommonDTO();

	public CricketUserCommonDTO getCricketUserCommonDTO() {
		return cricketUserCommonDTO;
	}

	public void setCricketUserCommonDTO(CricketUserCommonDTO cricketUserCommonDTO) {
		this.cricketUserCommonDTO = cricketUserCommonDTO;
	}

    
}
