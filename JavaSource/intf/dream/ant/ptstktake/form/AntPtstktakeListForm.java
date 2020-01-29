package intf.dream.ant.ptstktake.form;

import common.struts.BaseForm;
import intf.dream.ant.ptstktake.dto.AntPtstktakeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPtstktakeListForm"
 */
public class AntPtstktakeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPtstktakeCommonDTO antPtstktakeCommonDTO = new AntPtstktakeCommonDTO();

	public AntPtstktakeCommonDTO getAntPtstktakeCommonDTO() {
		return antPtstktakeCommonDTO;
	}

	public void setAntPtstktakeCommonDTO(AntPtstktakeCommonDTO antPtstktakeCommonDTO) {
		this.antPtstktakeCommonDTO = antPtstktakeCommonDTO;
	}

    
}
