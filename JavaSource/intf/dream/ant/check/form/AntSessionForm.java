package intf.dream.ant.check.form;

import common.struts.BaseForm;
import intf.dream.ant.check.dto.AntCheckCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antSessionForm"
 */
public class AntSessionForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntCheckCommonDTO antCheckCommonDTO = new AntCheckCommonDTO();

	public AntCheckCommonDTO getAntCheckCommonDTO() {
		return antCheckCommonDTO;
	}

	public void setAntCheckCommonDTO(AntCheckCommonDTO antCheckCommonDTO) {
		this.antCheckCommonDTO = antCheckCommonDTO;
	}

    
}
