package intf.dream.android.offline.pmi.patrol.form;

import common.struts.BaseForm;
import intf.dream.android.offline.pmi.patrol.dto.AnIfPmiPatrolCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anIfPmiPatrolListForm"
 */
public class AnIfPmiPatrolListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnIfPmiPatrolCommonDTO anIfPmiPatrolCommonDTO = new AnIfPmiPatrolCommonDTO();

	public AnIfPmiPatrolCommonDTO getAnIfPmiPatrolCommonDTO() {
		return anIfPmiPatrolCommonDTO;
	}

	public void setAnIfPmiPatrolCommonDTO(AnIfPmiPatrolCommonDTO anIfPmiPatrolCommonDTO) {
		this.anIfPmiPatrolCommonDTO = anIfPmiPatrolCommonDTO;
	}

    
}
