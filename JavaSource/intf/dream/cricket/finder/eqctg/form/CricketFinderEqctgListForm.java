package intf.dream.cricket.finder.eqctg.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.eqctg.dto.CricketFinderEqctgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderEqctgListForm"
 */
public class CricketFinderEqctgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderEqctgCommonDTO cricketFinderEqctgCommonDTO = new CricketFinderEqctgCommonDTO();

	public CricketFinderEqctgCommonDTO getCricketFinderEqctgCommonDTO() {
		return cricketFinderEqctgCommonDTO;
	}

	public void setCricketFinderEqctgCommonDTO(CricketFinderEqctgCommonDTO cricketFinderEqctgCommonDTO) {
		this.cricketFinderEqctgCommonDTO = cricketFinderEqctgCommonDTO;
	}

    
}
