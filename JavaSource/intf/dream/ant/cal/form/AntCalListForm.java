package intf.dream.ant.cal.form;

import common.struts.BaseForm;
import intf.dream.ant.cal.dto.AntCalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antCalListForm"
 */
public class AntCalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntCalCommonDTO antCalCommonDTO = new AntCalCommonDTO();

	public AntCalCommonDTO getAntCalCommonDTO() {
		return antCalCommonDTO;
	}

	public void setAntCalCommonDTO(AntCalCommonDTO antCalCommonDTO) {
		this.antCalCommonDTO = antCalCommonDTO;
	}

    
}
