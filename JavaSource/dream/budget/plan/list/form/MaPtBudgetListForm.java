package dream.budget.plan.list.form;

import common.struts.BaseForm;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 예산관리 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtBudgetListForm"
 */
public class MaPtBudgetListForm extends BaseForm
{    
    /** 공통 */
    private MaPtBudgetCommonDTO maPtBudgetCommonDTO = new MaPtBudgetCommonDTO();;

	public MaPtBudgetCommonDTO getMaPtBudgetCommonDTO() 
	{
		return maPtBudgetCommonDTO;
	}

	public void setMaPtBudgetCommonDTO(MaPtBudgetCommonDTO maPtBudgetCommonDTO) 
	{
		this.maPtBudgetCommonDTO = maPtBudgetCommonDTO;
	}

}
