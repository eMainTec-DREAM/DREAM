package intf.dream.bee.image.form;

import common.file.FileForm;
import intf.dream.bee.image.dto.BeeImageCommonDTO;

/**
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeImageListForm"
 */
public class BeeImageListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private BeeImageCommonDTO beeImageCommonDTO = new BeeImageCommonDTO();

	public BeeImageCommonDTO getBeeImageCommonDTO() {
		return beeImageCommonDTO;
	}
	public void setBeeImageCommonDTO(BeeImageCommonDTO beeImageCommonDTO) {
		this.beeImageCommonDTO = beeImageCommonDTO;
	}
}
