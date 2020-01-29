package intf.dream.bee.wodaily.form;

import common.file.FileForm;
import intf.dream.bee.wodaily.dto.BeeWoDailyCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeWoDailyListForm"
 */
public class BeeWoDailyListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private BeeWoDailyCommonDTO beeWoDailyCommonDTO = new BeeWoDailyCommonDTO();

	public BeeWoDailyCommonDTO getBeeWoDailyCommonDTO() {
		return beeWoDailyCommonDTO;
	}

	public void setBeeWoDailyCommonDTO(BeeWoDailyCommonDTO beeWoDailyCommonDTO) {
		this.beeWoDailyCommonDTO = beeWoDailyCommonDTO;
	}

    
}
