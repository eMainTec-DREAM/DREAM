package intf.dream.android.online.image.form;

import common.file.FileForm;
import intf.dream.android.online.image.dto.AnOnImageCommonDTO;

/**
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnImageListForm"
 */
public class AnOnImageListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnImageCommonDTO anOnImageCommonDTO = new AnOnImageCommonDTO();

	public AnOnImageCommonDTO getAnOnImageCommonDTO() {
		return anOnImageCommonDTO;
	}
	public void setAnOnImageCommonDTO(AnOnImageCommonDTO anOnImageCommonDTO) {
		this.anOnImageCommonDTO = anOnImageCommonDTO;
	}
}
