package intf.dream.ant.pmwork.form;

import common.struts.BaseForm;
import intf.dream.ant.pmwork.dto.AntPmworkCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPmworkListForm"
 */
public class AntPmworkListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPmworkCommonDTO antPmworkCommonDTO = new AntPmworkCommonDTO();

	public AntPmworkCommonDTO getAntPmworkCommonDTO() {
		return antPmworkCommonDTO;
	}

	public void setAntPmworkCommonDTO(AntPmworkCommonDTO antPmworkCommonDTO) {
		this.antPmworkCommonDTO = antPmworkCommonDTO;
	}

    
}
