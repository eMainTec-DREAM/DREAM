package intf.dream.android.online.pmwork.form;

import common.file.FileForm;
import intf.dream.android.online.pmwork.dto.AnOnPmworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnPmworkListForm"
 */
public class AnOnPmworkListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnPmworkCommonDTO anOnPmworkCommonDTO = new AnOnPmworkCommonDTO();

	public AnOnPmworkCommonDTO getAnOnPmworkCommonDTO() {
		return anOnPmworkCommonDTO;
	}

	public void setAnOnPmworkCommonDTO(AnOnPmworkCommonDTO anOnPmworkCommonDTO) {
		this.anOnPmworkCommonDTO = anOnPmworkCommonDTO;
	}

    
}
