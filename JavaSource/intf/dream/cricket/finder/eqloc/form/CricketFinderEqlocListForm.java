package intf.dream.cricket.finder.eqloc.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.eqloc.dto.CricketFinderEqlocCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderEqlocListForm"
 */
public class CricketFinderEqlocListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderEqlocCommonDTO cricketFinderEqlocCommonDTO = new CricketFinderEqlocCommonDTO();

	public CricketFinderEqlocCommonDTO getCricketFinderEqlocCommonDTO() {
		return cricketFinderEqlocCommonDTO;
	}

	public void setCricketFinderEqlocCommonDTO(CricketFinderEqlocCommonDTO cricketFinderEqlocCommonDTO) {
		this.cricketFinderEqlocCommonDTO = cricketFinderEqlocCommonDTO;
	}

    
}
