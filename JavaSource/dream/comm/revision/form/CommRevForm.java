package dream.comm.revision.form;

import common.struts.BaseForm;
import dream.comm.revision.dto.CommRevCommonDTO;

/**
 * 상세 Form
 * @author  jung7126
 * @version $Id: CommRevForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="commRevForm"
 */
public class CommRevForm extends BaseForm
{
    //========================================================================
    /** 공통 */ 
    private CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
    //========================================================================
    
	public CommRevCommonDTO getCommRevCommonDTO() {
		return commRevCommonDTO;
	}
	public void setCommRevCommonDTO(CommRevCommonDTO commRevCommonDTO) {
		this.commRevCommonDTO = commRevCommonDTO;
	}
}
