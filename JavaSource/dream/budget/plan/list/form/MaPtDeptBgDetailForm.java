package dream.budget.plan.list.form;

import common.struts.BaseForm;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;
/**
 * �μ��� ����ݾ�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtDeptBgDetailForm"
 */
public class MaPtDeptBgDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPtBudgetCommonDTO maPtBudgetCommonDTO = new MaPtBudgetCommonDTO();
	/** �� DTO  */
    private MaPtDeptBgDetailDTO maPtDeptBgDetailDTO = new MaPtDeptBgDetailDTO();

	public MaPtDeptBgDetailDTO getMaPtDeptBgDetailDTO() {
		return maPtDeptBgDetailDTO;
	}
	public void setMaPtDeptBgDetailDTO(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO) {
		this.maPtDeptBgDetailDTO = maPtDeptBgDetailDTO;
	}
	public MaPtBudgetCommonDTO getMaPtBudgetCommonDTO() {
		return maPtBudgetCommonDTO;
	}
	public void setMaPtBudgetCommonDTO(MaPtBudgetCommonDTO maPtBudgetCommonDTO) {
		this.maPtBudgetCommonDTO = maPtBudgetCommonDTO;
	}
}
