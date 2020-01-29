package dream.work.let.form;

import common.struts.BaseForm;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;

/**
 * �����۾�- �� Form
 * @author  syyang
 * @version $Id: WorkLetDetailForm.java,v 1.0 2015/12/02 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="workLetDetailForm"
 */
public class WorkLetDetailForm extends BaseForm
{
    //========================================================================
    /** �����۾� ���� */ 
    private WorkLetCommonDTO workLetCommonDTO = new WorkLetCommonDTO();
    //========================================================================
    /** �����۾� �� */
    private WorkLetDetailDTO workLetDetailDTO = new WorkLetDetailDTO();
    
    
	public WorkLetCommonDTO getWorkLetCommonDTO() {
		return workLetCommonDTO;
	}

    public void setWorkLetCommonDTO(WorkLetCommonDTO workLetCommonDTO) {
		this.workLetCommonDTO = workLetCommonDTO;
	}

	public WorkLetDetailDTO getWorkLetDetailDTO() {
		return workLetDetailDTO;
	}

	public void setWorkLetDetailDTO(WorkLetDetailDTO workLetDetailDTO) {
		this.workLetDetailDTO = workLetDetailDTO;
	}
}
