package intf.dream.ant.pmi.routine.form;

import common.struts.BaseForm;
import intf.dream.ant.pmi.routine.dto.AntPmiRoutineCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antPmiRoutineListForm"
 */
public class AntPmiRoutineListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntPmiRoutineCommonDTO antPmiRoutineCommonDTO = new AntPmiRoutineCommonDTO();

	public AntPmiRoutineCommonDTO getAntPmiRoutineCommonDTO() {
		return antPmiRoutineCommonDTO;
	}

	public void setAntPmiRoutineCommonDTO(AntPmiRoutineCommonDTO antPmiRoutineCommonDTO) {
		this.antPmiRoutineCommonDTO = antPmiRoutineCommonDTO;
	}

    
}
