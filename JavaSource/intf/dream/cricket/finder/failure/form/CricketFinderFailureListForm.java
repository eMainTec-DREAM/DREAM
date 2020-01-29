package intf.dream.cricket.finder.failure.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.failure.dto.CricketFinderFailureCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderFailureListForm"
 */
public class CricketFinderFailureListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderFailureCommonDTO cricketFinderFailureCommonDTO = new CricketFinderFailureCommonDTO();

	public CricketFinderFailureCommonDTO getCricketFinderFailureCommonDTO() {
		return cricketFinderFailureCommonDTO;
	}

	public void setCricketFinderFailureCommonDTO(CricketFinderFailureCommonDTO cricketFinderFailureCommonDTO) {
		this.cricketFinderFailureCommonDTO = cricketFinderFailureCommonDTO;
	}

    
}
