package intf.dream.bee.woreq.form;

import common.file.FileForm;
import intf.dream.bee.woreq.dto.BeeWoReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeWoReqListForm"
 */
public class BeeWoReqListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private BeeWoReqCommonDTO beeWoReqCommonDTO = new BeeWoReqCommonDTO();

	public BeeWoReqCommonDTO getBeeWoReqCommonDTO() {
		return beeWoReqCommonDTO;
	}

	public void setBeeWoReqCommonDTO(BeeWoReqCommonDTO beeWoReqCommonDTO) {
		this.beeWoReqCommonDTO = beeWoReqCommonDTO;
	}

    
}
