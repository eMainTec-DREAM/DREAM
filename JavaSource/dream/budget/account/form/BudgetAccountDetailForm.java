package dream.budget.account.form;

import common.struts.BaseForm;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;

/**
 * 예산계정- 상세 Form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="budgetAccountDetailForm"
 */
public class BudgetAccountDetailForm extends BaseForm
{
    //========================================================================
    /** 예산계정 공통 */ 
    private BudgetAccountCommonDTO budgetAccountCommonDTO = new BudgetAccountCommonDTO();
    //========================================================================
    /** 예산계정 상세 */
    private BudgetAccountDetailDTO budgetAccountDetailDTO = new BudgetAccountDetailDTO();
    
	public BudgetAccountCommonDTO getBudgetAccountCommonDTO() {
		return budgetAccountCommonDTO;
	}

	public void setBudgetAccountCommonDTO(BudgetAccountCommonDTO budgetAccountCommonDTO) {
		this.budgetAccountCommonDTO = budgetAccountCommonDTO;
	}

	public BudgetAccountDetailDTO getBudgetAccountDetailDTO() {
		return budgetAccountDetailDTO;
	}

	public void setBudgetAccountDetailDTO(BudgetAccountDetailDTO budgetAccountDetailDTO) {
		this.budgetAccountDetailDTO = budgetAccountDetailDTO;
	}
}
