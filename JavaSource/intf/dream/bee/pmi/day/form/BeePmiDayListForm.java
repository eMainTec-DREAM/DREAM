package intf.dream.bee.pmi.day.form;

import common.struts.BaseForm;
import intf.dream.bee.pmi.day.dto.BeePmiDayCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beePmiDayListForm"
 */
public class BeePmiDayListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeePmiDayCommonDTO beePmiDayCommonDTO = new BeePmiDayCommonDTO();

	public BeePmiDayCommonDTO getBeePmiDayCommonDTO() {
		return beePmiDayCommonDTO;
	}

	public void setBeePmiDayCommonDTO(BeePmiDayCommonDTO beePmiDayCommonDTO) {
		this.beePmiDayCommonDTO = beePmiDayCommonDTO;
	}

    
}
