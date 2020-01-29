package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� ǥ�ر�
 * @author  kim2107
 * @version $Id: WorkListGnlCaEqDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListGnlCaEqDetailForm"
 */
public class WorkListGnlCaEqDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� �۾��� �� DTO  */
    private WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = new WorkListGnlCaEqDetailDTO();
    
	public WorkListGnlCaEqDetailDTO getWorkListGnlCaEqDetailDTO() {
		return workListGnlCaEqDetailDTO;
	}
	public void setWorkListGnlCaEqDetailDTO(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO) {
		this.workListGnlCaEqDetailDTO = workListGnlCaEqDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
