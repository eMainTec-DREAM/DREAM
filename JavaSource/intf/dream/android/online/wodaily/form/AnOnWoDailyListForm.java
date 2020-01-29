package intf.dream.android.online.wodaily.form;

import common.file.FileForm;
import intf.dream.android.online.wodaily.dto.AnOnWoDailyCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnWoDailyListForm"
 */
public class AnOnWoDailyListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnWoDailyCommonDTO anOnWoDailyCommonDTO = new AnOnWoDailyCommonDTO();

	public AnOnWoDailyCommonDTO getAnOnWoDailyCommonDTO() {
		return anOnWoDailyCommonDTO;
	}

	public void setAnOnWoDailyCommonDTO(AnOnWoDailyCommonDTO anOnWoDailyCommonDTO) {
		this.anOnWoDailyCommonDTO = anOnWoDailyCommonDTO;
	}

    
}
