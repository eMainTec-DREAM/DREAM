package intf.dream.android.online.pmi.routine.form;

import common.struts.BaseForm;
import intf.dream.android.online.pmi.routine.dto.AnOnPmiRoutineCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnPmiRoutineListForm"
 */
public class AnOnPmiRoutineListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnPmiRoutineCommonDTO anOnPmiRoutineCommonDTO = new AnOnPmiRoutineCommonDTO();

	public AnOnPmiRoutineCommonDTO getAnOnPmiRoutineCommonDTO() {
		return anOnPmiRoutineCommonDTO;
	}

	public void setAnOnPmiRoutineCommonDTO(AnOnPmiRoutineCommonDTO anOnPmiRoutineCommonDTO) {
		this.anOnPmiRoutineCommonDTO = anOnPmiRoutineCommonDTO;
	}

    
}
