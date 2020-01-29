package intf.dream.bee.finder.usrcode.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.usrcode.dto.BeeFinderUsrcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderUsrcodeListForm"
 */
public class BeeFinderUsrcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderUsrcodeCommonDTO beeFinderUsrcodeCommonDTO = new BeeFinderUsrcodeCommonDTO();

	public BeeFinderUsrcodeCommonDTO getBeeFinderUsrcodeCommonDTO() {
		return beeFinderUsrcodeCommonDTO;
	}

	public void setBeeFinderUsrcodeCommonDTO(BeeFinderUsrcodeCommonDTO beeFinderUsrcodeCommonDTO) {
		this.beeFinderUsrcodeCommonDTO = beeFinderUsrcodeCommonDTO;
	}

    
}
