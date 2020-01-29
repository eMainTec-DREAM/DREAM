package intf.dream.ant.initcode.form;

import common.struts.BaseForm;
import intf.dream.ant.initcode.dto.AntInitcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antInitcodeListForm"
 */
public class AntInitcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntInitcodeCommonDTO antInitcodeCommonDTO = new AntInitcodeCommonDTO();

	public AntInitcodeCommonDTO getAntInitcodeCommonDTO() {
		return antInitcodeCommonDTO;
	}

	public void setAntInitcodeCommonDTO(AntInitcodeCommonDTO antInitcodeCommonDTO) {
		this.antInitcodeCommonDTO = antInitcodeCommonDTO;
	}

    
}
