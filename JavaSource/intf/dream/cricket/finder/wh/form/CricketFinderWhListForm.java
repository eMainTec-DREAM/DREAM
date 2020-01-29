package intf.dream.cricket.finder.wh.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.wh.dto.CricketFinderWhCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="cricketFinderWhListForm"
 */
public class CricketFinderWhListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderWhCommonDTO cricketFinderWhCommonDTO = new CricketFinderWhCommonDTO();

	public CricketFinderWhCommonDTO getCricketFinderWhCommonDTO() {
		return cricketFinderWhCommonDTO;
	}

	public void setCricketFinderWhCommonDTO(CricketFinderWhCommonDTO cricketFinderWhCommonDTO) {
		this.cricketFinderWhCommonDTO = cricketFinderWhCommonDTO;
	}

    
}
