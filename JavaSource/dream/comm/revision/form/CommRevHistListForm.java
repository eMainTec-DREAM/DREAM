package dream.comm.revision.form;

import common.struts.BaseForm;
import dream.comm.revision.dto.CommRevHistCommonDTO;

/**
 * ��/���� �����̷�
 * @author  kim21017
 * @version $Id: CommRevHistListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="commRevHistListForm"
 */
public class CommRevHistListForm extends BaseForm
{    
    //===============================================================
	/** ��/���� �����̷� Common */
    private CommRevHistCommonDTO commRevHistCommonDTO = new CommRevHistCommonDTO();

	public CommRevHistCommonDTO getCommRevHistCommonDTO() {
		return commRevHistCommonDTO;
	}
	public void setCommRevHistCommonDTO(CommRevHistCommonDTO commRevHistCommonDTO) {
		this.commRevHistCommonDTO = commRevHistCommonDTO;
	}

}
