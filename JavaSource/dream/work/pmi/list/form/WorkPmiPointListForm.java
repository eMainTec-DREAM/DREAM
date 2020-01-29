package dream.work.pmi.list.form;

import common.struts.BaseForm;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * �����۾� ���� ���
 * @author  kim21017
 * @version $Id: WorkPmiPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmiPointListForm"
 */
public class WorkPmiPointListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    /**  �۾���� �˻��׸� ���  */
    private WorkPmiPointListDTO workPmiPointListDTO = new WorkPmiPointListDTO();
	

	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public WorkPmiPointListDTO getWorkPmiPointListDTO() {
		return workPmiPointListDTO;
	}

	public void setWorkPmiPointListDTO(WorkPmiPointListDTO workPmiPointListDTO) {
		this.workPmiPointListDTO = workPmiPointListDTO;
	}
}
