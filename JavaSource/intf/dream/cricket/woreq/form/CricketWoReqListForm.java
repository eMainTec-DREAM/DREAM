package intf.dream.cricket.woreq.form;

import common.file.FileForm;
import intf.dream.cricket.woreq.dto.CricketWoReqCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketWoReqListForm"
 */
public class CricketWoReqListForm extends FileForm
{    
    //===============================================================
    /** °øÅë */
    private CricketWoReqCommonDTO cricketWoReqCommonDTO = new CricketWoReqCommonDTO();

	public CricketWoReqCommonDTO getCricketWoReqCommonDTO() {
		return cricketWoReqCommonDTO;
	}

	public void setCricketWoReqCommonDTO(CricketWoReqCommonDTO cricketWoReqCommonDTO) {
		this.cricketWoReqCommonDTO = cricketWoReqCommonDTO;
	}

    
}
