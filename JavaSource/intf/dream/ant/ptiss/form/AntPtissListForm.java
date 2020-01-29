package intf.dream.ant.ptiss.form;

import common.struts.BaseForm;
import intf.dream.ant.ptiss.dto.AntPtissCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPtissListForm"
 */
public class AntPtissListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPtissCommonDTO antPtissCommonDTO = new AntPtissCommonDTO();

	public AntPtissCommonDTO getAntPtissCommonDTO() {
		return antPtissCommonDTO;
	}

	public void setAntPtissCommonDTO(AntPtissCommonDTO antPtissCommonDTO) {
		this.antPtissCommonDTO = antPtissCommonDTO;
	}

    
}
