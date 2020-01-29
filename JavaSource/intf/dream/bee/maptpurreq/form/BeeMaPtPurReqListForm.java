package intf.dream.bee.maptpurreq.form;

import common.struts.BaseForm;
import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeMaPtPurReqListForm"
 */
public class BeeMaPtPurReqListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO = new BeeMaPtPurReqCommonDTO();

	public BeeMaPtPurReqCommonDTO getBeeMaPtPurReqCommonDTO() {
		return beeMaPtPurReqCommonDTO;
	}

	public void setBeeMaPtPurReqCommonDTO(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO) {
		this.beeMaPtPurReqCommonDTO = beeMaPtPurReqCommonDTO;
	}

    
}
