package dream.comm.revision.form;

import common.struts.BaseForm;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.dto.CommRevHistDetailDTO;

/**
 * �� Form
 * @author  jung7126
 * @version $Id: CommRevHistDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="commRevHistDetailForm"
 */
public class CommRevHistDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private CommRevHistCommonDTO commRevHistCommonDTO = new CommRevHistCommonDTO();
    //========================================================================
    /** �� DTO */ 
    private CommRevHistDetailDTO commRevHistDetailDTO = new CommRevHistDetailDTO();

    
	public CommRevHistCommonDTO getCommRevHistCommonDTO() {
		return commRevHistCommonDTO;
	}
	public void setCommRevHistCommonDTO(CommRevHistCommonDTO commRevHistCommonDTO) {
		this.commRevHistCommonDTO = commRevHistCommonDTO;
	}
	public CommRevHistDetailDTO getCommRevHistDetailDTO() {
		return commRevHistDetailDTO;
	}
	public void setCommRevHistDetailDTO(CommRevHistDetailDTO commRevHistDetailDTO) {
		this.commRevHistDetailDTO = commRevHistDetailDTO;
	}
}
