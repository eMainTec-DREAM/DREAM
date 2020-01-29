package intf.dream.cricket.common.form;

import common.struts.BaseForm;
import intf.dream.cricket.common.dto.CricketCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketCommonListForm"
 */
public class CricketCommonListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketCommonDTO cricketCommonDTO = new CricketCommonDTO();

	public CricketCommonDTO getCricketCommonDTO() {
		return cricketCommonDTO;
	}

	public void setCricketCommonDTO(CricketCommonDTO cricketCommonDTO) {
		this.cricketCommonDTO = cricketCommonDTO;
	}
}
