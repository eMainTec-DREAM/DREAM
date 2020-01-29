package intf.dream.cricket.finder.emp.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.emp.dto.CricketFinderEmpCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderEmpListForm"
 */
public class CricketFinderEmpListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderEmpCommonDTO cricketFinderEmpCommonDTO = new CricketFinderEmpCommonDTO();

	public CricketFinderEmpCommonDTO getCricketFinderEmpCommonDTO() {
		return cricketFinderEmpCommonDTO;
	}

	public void setCricketFinderEmpCommonDTO(CricketFinderEmpCommonDTO cricketFinderEmpCommonDTO) {
		this.cricketFinderEmpCommonDTO = cricketFinderEmpCommonDTO;
	}

    
}
