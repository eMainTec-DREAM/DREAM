package intf.dream.ant.pmi.day.form;

import common.struts.BaseForm;
import intf.dream.ant.pmi.day.dto.AntPmiDayCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPmiDayListForm"
 */
public class AntPmiDayListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPmiDayCommonDTO antPmiDayCommonDTO = new AntPmiDayCommonDTO();

	public AntPmiDayCommonDTO getAntPmiDayCommonDTO() {
		return antPmiDayCommonDTO;
	}

	public void setAntPmiDayCommonDTO(AntPmiDayCommonDTO antPmiDayCommonDTO) {
		this.antPmiDayCommonDTO = antPmiDayCommonDTO;
	}

    
}
