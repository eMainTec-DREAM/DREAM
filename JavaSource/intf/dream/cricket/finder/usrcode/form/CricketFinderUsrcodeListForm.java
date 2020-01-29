package intf.dream.cricket.finder.usrcode.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.usrcode.dto.CricketFinderUsrcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderUsrcodeListForm"
 */
public class CricketFinderUsrcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderUsrcodeCommonDTO cricketFinderUsrcodeCommonDTO = new CricketFinderUsrcodeCommonDTO();

	public CricketFinderUsrcodeCommonDTO getCricketFinderUsrcodeCommonDTO() {
		return cricketFinderUsrcodeCommonDTO;
	}

	public void setCricketFinderUsrcodeCommonDTO(CricketFinderUsrcodeCommonDTO cricketFinderUsrcodeCommonDTO) {
		this.cricketFinderUsrcodeCommonDTO = cricketFinderUsrcodeCommonDTO;
	}

    
}
