package intf.dream.cricket.finder.dept.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.dept.dto.CricketFinderDeptCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderDeptListForm"
 */
public class CricketFinderDeptListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderDeptCommonDTO cricketFinderDeptCommonDTO = new CricketFinderDeptCommonDTO();

	public CricketFinderDeptCommonDTO getCricketFinderDeptCommonDTO() {
		return cricketFinderDeptCommonDTO;
	}

	public void setCricketFinderDeptCommonDTO(CricketFinderDeptCommonDTO cricketFinderDeptCommonDTO) {
		this.cricketFinderDeptCommonDTO = cricketFinderDeptCommonDTO;
	}

    
}
