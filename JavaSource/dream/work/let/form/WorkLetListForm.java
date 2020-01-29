package dream.work.let.form;

import common.struts.BaseForm;
import dream.work.let.dto.WorkLetCommonDTO;

/**
 * �����۾� - ��� form
 * @author  syyang
 * @version $Id: WorkLetListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workLetListForm"
 */
public class WorkLetListForm extends BaseForm
{    
    //===============================================================
    /** �����۾� ���� */
    private WorkLetCommonDTO workLetCommonDTO = new WorkLetCommonDTO();
    
	public WorkLetCommonDTO getWorkLetCommonDTO() {
		return workLetCommonDTO;
	}

	public void setWorkLetCommonDTO(WorkLetCommonDTO workLetCommonDTO) {
		this.workLetCommonDTO = workLetCommonDTO;
	}
}
