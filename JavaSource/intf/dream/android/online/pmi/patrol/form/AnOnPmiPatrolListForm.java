package intf.dream.android.online.pmi.patrol.form;

import common.struts.BaseForm;
import intf.dream.android.online.pmi.patrol.dto.AnOnPmiPatrolCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnPmiPatrolListForm"
 */
public class AnOnPmiPatrolListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnPmiPatrolCommonDTO anOnPmiPatrolCommonDTO = new AnOnPmiPatrolCommonDTO();

	public AnOnPmiPatrolCommonDTO getAnOnPmiPatrolCommonDTO() {
		return anOnPmiPatrolCommonDTO;
	}

	public void setAnOnPmiPatrolCommonDTO(AnOnPmiPatrolCommonDTO anOnPmiPatrolCommonDTO) {
		this.anOnPmiPatrolCommonDTO = anOnPmiPatrolCommonDTO;
	}

    
}
