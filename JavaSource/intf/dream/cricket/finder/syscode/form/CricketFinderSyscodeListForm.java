package intf.dream.cricket.finder.syscode.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.syscode.dto.CricketFinderSyscodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderSyscodeListForm"
 */
public class CricketFinderSyscodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderSyscodeCommonDTO cricketFinderSyscodeCommonDTO = new CricketFinderSyscodeCommonDTO();

	public CricketFinderSyscodeCommonDTO getCricketFinderSyscodeCommonDTO() {
		return cricketFinderSyscodeCommonDTO;
	}

	public void setCricketFinderSyscodeCommonDTO(CricketFinderSyscodeCommonDTO cricketFinderSyscodeCommonDTO) {
		this.cricketFinderSyscodeCommonDTO = cricketFinderSyscodeCommonDTO;
	}

    
}
