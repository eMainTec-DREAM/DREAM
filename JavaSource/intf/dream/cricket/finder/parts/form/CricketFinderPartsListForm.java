package intf.dream.cricket.finder.parts.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.parts.dto.CricketFinderPartsCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderPartsListForm"
 */
public class CricketFinderPartsListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderPartsCommonDTO cricketFinderPartsCommonDTO = new CricketFinderPartsCommonDTO();

	public CricketFinderPartsCommonDTO getCricketFinderPartsCommonDTO() {
		return cricketFinderPartsCommonDTO;
	}

	public void setCricketFinderPartsCommonDTO(CricketFinderPartsCommonDTO cricketFinderPartsCommonDTO) {
		this.cricketFinderPartsCommonDTO = cricketFinderPartsCommonDTO;
	}

    
}
