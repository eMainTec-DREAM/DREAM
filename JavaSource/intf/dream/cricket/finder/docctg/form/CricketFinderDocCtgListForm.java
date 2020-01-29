package intf.dream.cricket.finder.docctg.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.docctg.dto.CricketFinderDocCtgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderDocCtgListForm"
 */
public class CricketFinderDocCtgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderDocCtgCommonDTO cricketFinderDocCtgCommonDTO = new CricketFinderDocCtgCommonDTO();

	public CricketFinderDocCtgCommonDTO getCricketFinderDocCtgCommonDTO() {
		return cricketFinderDocCtgCommonDTO;
	}

	public void setCricketFinderDocCtgCommonDTO(CricketFinderDocCtgCommonDTO cricketFinderDocCtgCommonDTO) {
		this.cricketFinderDocCtgCommonDTO = cricketFinderDocCtgCommonDTO;
	}

    
}
