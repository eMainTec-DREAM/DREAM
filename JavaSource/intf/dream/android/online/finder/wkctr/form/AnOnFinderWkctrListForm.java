package intf.dream.android.online.finder.wkctr.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.wkctr.dto.AnOnFinderWkctrCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="anOnFinderWkctrListForm"
 */
public class AnOnFinderWkctrListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderWkctrCommonDTO anOnFinderWkctrCommonDTO = new AnOnFinderWkctrCommonDTO();

	public AnOnFinderWkctrCommonDTO getAnOnFinderWkctrCommonDTO() {
		return anOnFinderWkctrCommonDTO;
	}

	public void setAnOnFinderWkctrCommonDTO(AnOnFinderWkctrCommonDTO anOnFinderWkctrCommonDTO) {
		this.anOnFinderWkctrCommonDTO = anOnFinderWkctrCommonDTO;
	}

    
}
