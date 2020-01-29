package intf.dream.bee.pmi.routine.form;

import common.struts.BaseForm;
import intf.dream.bee.pmi.routine.dto.BeePmiRoutineCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beePmiRoutineListForm"
 */
public class BeePmiRoutineListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeePmiRoutineCommonDTO beePmiRoutineCommonDTO = new BeePmiRoutineCommonDTO();

	public BeePmiRoutineCommonDTO getBeePmiRoutineCommonDTO() {
		return beePmiRoutineCommonDTO;
	}

	public void setBeePmiRoutineCommonDTO(BeePmiRoutineCommonDTO beePmiRoutineCommonDTO) {
		this.beePmiRoutineCommonDTO = beePmiRoutineCommonDTO;
	}

    
}
