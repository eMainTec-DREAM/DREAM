package intf.dream.ant.unplanwork.form;

import common.struts.BaseForm;
import intf.dream.ant.unplanwork.dto.AntUnplanworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antUnplanworkListForm"
 */
public class AntUnplanworkListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntUnplanworkCommonDTO antUnplanworkCommonDTO = new AntUnplanworkCommonDTO();

	public AntUnplanworkCommonDTO getAntUnplanworkCommonDTO() {
		return antUnplanworkCommonDTO;
	}

	public void setAntUnplanworkCommonDTO(AntUnplanworkCommonDTO antUnplanworkCommonDTO) {
		this.antUnplanworkCommonDTO = antUnplanworkCommonDTO;
	}

    
}
