package intf.dream.ant.ptstock.form;

import common.struts.BaseForm;
import intf.dream.ant.ptstock.dto.AntPtstockCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPtstockListForm"
 */
public class AntPtstockListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPtstockCommonDTO antPtstockCommonDTO = new AntPtstockCommonDTO();

	public AntPtstockCommonDTO getAntPtstockCommonDTO() {
		return antPtstockCommonDTO;
	}

	public void setAntPtstockCommonDTO(AntPtstockCommonDTO antPtstockCommonDTO) {
		this.antPtstockCommonDTO = antPtstockCommonDTO;
	}

    
}
