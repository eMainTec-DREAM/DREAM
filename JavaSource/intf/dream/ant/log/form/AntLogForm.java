package intf.dream.ant.log.form;

import common.struts.BaseForm;
import intf.dream.ant.log.dto.AntLogCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antLogForm"
 */
public class AntLogForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntLogCommonDTO antLogCommonDTO = new AntLogCommonDTO();

	public AntLogCommonDTO getAntLogCommonDTO() {
		return antLogCommonDTO;
	}

	public void setAntLogCommonDTO(AntLogCommonDTO antLogCommonDTO) {
		this.antLogCommonDTO = antLogCommonDTO;
	}

    
}
