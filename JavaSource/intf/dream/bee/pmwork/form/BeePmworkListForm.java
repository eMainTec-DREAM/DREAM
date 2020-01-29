package intf.dream.bee.pmwork.form;

import common.file.FileForm;
import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beePmworkListForm"
 */
public class BeePmworkListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private BeePmworkCommonDTO beePmworkCommonDTO = new BeePmworkCommonDTO();

	public BeePmworkCommonDTO getBeePmworkCommonDTO() {
		return beePmworkCommonDTO;
	}

	public void setBeePmworkCommonDTO(BeePmworkCommonDTO beePmworkCommonDTO) {
		this.beePmworkCommonDTO = beePmworkCommonDTO;
	}

    
}
