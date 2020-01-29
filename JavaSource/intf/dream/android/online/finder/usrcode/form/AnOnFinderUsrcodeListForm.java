package intf.dream.android.online.finder.usrcode.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.usrcode.dto.AnOnFinderUsrcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderUsrcodeListForm"
 */
public class AnOnFinderUsrcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderUsrcodeCommonDTO anOnFinderUsrcodeCommonDTO = new AnOnFinderUsrcodeCommonDTO();

	public AnOnFinderUsrcodeCommonDTO getAnOnFinderUsrcodeCommonDTO() {
		return anOnFinderUsrcodeCommonDTO;
	}

	public void setAnOnFinderUsrcodeCommonDTO(AnOnFinderUsrcodeCommonDTO anOnFinderUsrcodeCommonDTO) {
		this.anOnFinderUsrcodeCommonDTO = anOnFinderUsrcodeCommonDTO;
	}

    
}
