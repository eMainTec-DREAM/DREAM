package intf.dream.ant.pmi.patrol.form;

import common.struts.BaseForm;
import intf.dream.ant.pmi.patrol.dto.AntPmiPatrolCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPmiPatrolListForm"
 */
public class AntPmiPatrolListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPmiPatrolCommonDTO antPmiPatrolCommonDTO = new AntPmiPatrolCommonDTO();

	public AntPmiPatrolCommonDTO getAntPmiPatrolCommonDTO() {
		return antPmiPatrolCommonDTO;
	}

	public void setAntPmiPatrolCommonDTO(AntPmiPatrolCommonDTO antPmiPatrolCommonDTO) {
		this.antPmiPatrolCommonDTO = antPmiPatrolCommonDTO;
	}

    
}
