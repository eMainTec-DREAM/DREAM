package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� ������
 * @author  kim2107
 * @version $Id: WorkListGnlCavalDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListGnlCavalDetailForm"
 */
public class WorkListGnlCavalDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� ������ �� DTO  */
    private WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = new WorkListGnlCavalDetailDTO();
    
	public WorkListGnlCavalDetailDTO getWorkListGnlCavalDetailDTO() {
		return workListGnlCavalDetailDTO;
	}
	public void setWorkListGnlCavalDetailDTO(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO) {
		this.workListGnlCavalDetailDTO = workListGnlCavalDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
