package intf.dream.cricket.finder.eqasmb.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.eqasmb.dto.CricketFinderEqAsmbCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderEqAsmbListForm"
 */
public class CricketFinderEqAsmbListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderEqAsmbCommonDTO cricketFinderEqAsmbCommonDTO = new CricketFinderEqAsmbCommonDTO();

	public CricketFinderEqAsmbCommonDTO getCricketFinderEqAsmbCommonDTO() {
		return cricketFinderEqAsmbCommonDTO;
	}

	public void setCricketFinderEqAsmbCommonDTO(CricketFinderEqAsmbCommonDTO cricketFinderEqAsmbCommonDTO) {
		this.cricketFinderEqAsmbCommonDTO = cricketFinderEqAsmbCommonDTO;
	}

    
}
