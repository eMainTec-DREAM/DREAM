package dream.budget.plan.list.form;

import common.struts.BaseForm;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 부서별 예산금액 목록
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtDeptBgListForm"
 */
public class MaPtDeptBgListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPtBudgetCommonDTO maPtBudgetCommonDTO = new MaPtBudgetCommonDTO();
 
	public MaPtBudgetCommonDTO getMaPtBudgetCommonDTO() {
		return maPtBudgetCommonDTO;
	}

	public void setMaPtBudgetCommonDTO(MaPtBudgetCommonDTO maPtBudgetCommonDTO) {
		this.maPtBudgetCommonDTO = maPtBudgetCommonDTO;
	}
}
