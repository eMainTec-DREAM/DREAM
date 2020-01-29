package intf.dream.bee.pmi.part.form;

import common.struts.BaseForm;
import intf.dream.bee.pmi.part.dto.BeePmiPartCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beePmiPartListForm"
 */
public class BeePmiPartListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeePmiPartCommonDTO beePmiPartCommonDTO = new BeePmiPartCommonDTO();

	public BeePmiPartCommonDTO getBeePmiPartCommonDTO() {
		return beePmiPartCommonDTO;
	}

	public void setBeePmiPartCommonDTO(BeePmiPartCommonDTO beePmiPartCommonDTO) {
		this.beePmiPartCommonDTO = beePmiPartCommonDTO;
	}

}
