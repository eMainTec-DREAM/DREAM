package intf.dream.cricket.initcode.form;

import common.struts.BaseForm;
import intf.dream.cricket.initcode.dto.CricketInitcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketInitcodeListForm"
 */
public class CricketInitcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketInitcodeCommonDTO cricketInitcodeCommonDTO = new CricketInitcodeCommonDTO();

	public CricketInitcodeCommonDTO getCricketInitcodeCommonDTO() {
		return cricketInitcodeCommonDTO;
	}

	public void setCricketInitcodeCommonDTO(CricketInitcodeCommonDTO cricketInitcodeCommonDTO) {
		this.cricketInitcodeCommonDTO = cricketInitcodeCommonDTO;
	}

    
}
