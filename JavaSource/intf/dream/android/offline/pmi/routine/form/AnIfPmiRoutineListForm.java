package intf.dream.android.offline.pmi.routine.form;

import common.struts.BaseForm;
import intf.dream.android.offline.pmi.routine.dto.AnIfPmiRoutineCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPmiRoutineListForm"
 */
public class AnIfPmiRoutineListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPmiRoutineCommonDTO anIfPmiRoutineCommonDTO = new AnIfPmiRoutineCommonDTO();

	public AnIfPmiRoutineCommonDTO getAnIfPmiRoutineCommonDTO() {
		return anIfPmiRoutineCommonDTO;
	}

	public void setAnIfPmiRoutineCommonDTO(AnIfPmiRoutineCommonDTO anIfPmiRoutineCommonDTO) {
		this.anIfPmiRoutineCommonDTO = anIfPmiRoutineCommonDTO;
	}

    
}
