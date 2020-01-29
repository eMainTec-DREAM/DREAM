package intf.dream.bee.pmi.patrol.form;

import common.struts.BaseForm;
import intf.dream.bee.pmi.patrol.dto.BeePmiPatrolCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beePmiPatrolListForm"
 */
public class BeePmiPatrolListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeePmiPatrolCommonDTO beePmiPatrolCommonDTO = new BeePmiPatrolCommonDTO();

	public BeePmiPatrolCommonDTO getBeePmiPatrolCommonDTO() {
		return beePmiPatrolCommonDTO;
	}

	public void setBeePmiPatrolCommonDTO(BeePmiPatrolCommonDTO beePmiPatrolCommonDTO) {
		this.beePmiPatrolCommonDTO = beePmiPatrolCommonDTO;
	}

    
}
