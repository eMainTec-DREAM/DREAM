package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� �۾��� ���
 * @author  kim21017
 * @version $Id: WorkListCavalListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workListCavalListForm"
 */
public class WorkListCavalListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� �۾��� ���  */
    private WorkListCavalListDTO workListCavalListDTO = new WorkListCavalListDTO();
	

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public WorkListCavalListDTO getWorkListCavalListDTO() {
		return workListCavalListDTO;
	}

	public void setWorkListCavalListDTO(WorkListCavalListDTO workListCavalListDTO) {
		this.workListCavalListDTO = workListCavalListDTO;
	}
}
