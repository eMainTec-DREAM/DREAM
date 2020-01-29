package intf.dream.cricket.finder.wkctr.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.wkctr.dto.CricketFinderWkctrCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="cricketFinderWkctrListForm"
 */
public class CricketFinderWkctrListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderWkctrCommonDTO cricketFinderWkctrCommonDTO = new CricketFinderWkctrCommonDTO();

	public CricketFinderWkctrCommonDTO getCricketFinderWkctrCommonDTO() {
		return cricketFinderWkctrCommonDTO;
	}

	public void setCricketFinderWkctrCommonDTO(CricketFinderWkctrCommonDTO cricketFinderWkctrCommonDTO) {
		this.cricketFinderWkctrCommonDTO = cricketFinderWkctrCommonDTO;
	}

    
}
