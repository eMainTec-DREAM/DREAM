package intf.dream.ant.pmi.part.form;

import common.struts.BaseForm;
import intf.dream.ant.pmi.part.dto.AntPmiPartCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPmiPartListForm"
 */
public class AntPmiPartListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPmiPartCommonDTO antPmiPartCommonDTO = new AntPmiPartCommonDTO();

	public AntPmiPartCommonDTO getAntPmiPartCommonDTO() {
		return antPmiPartCommonDTO;
	}

	public void setAntPmiPartCommonDTO(AntPmiPartCommonDTO antPmiPartCommonDTO) {
		this.antPmiPartCommonDTO = antPmiPartCommonDTO;
	}

    
}
