package dream.budget.account.form;

import common.struts.BaseForm;
import dream.budget.account.dto.BudgetAccountCommonDTO;

/**
 * ������� - ��� form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="budgetAccountListForm"
 */
public class BudgetAccountListForm extends BaseForm
{    
    //===============================================================
    /** ������� ���� */
    private BudgetAccountCommonDTO budgetAccountCommonDTO = new BudgetAccountCommonDTO();;
    
	public BudgetAccountCommonDTO getBudgetAccountCommonDTO() 
	{
		return budgetAccountCommonDTO;
	}

	public void setBudgetAccountCommonDTO(BudgetAccountCommonDTO budgetAccountCommonDTO) 
	{
		this.budgetAccountCommonDTO = budgetAccountCommonDTO;
	}
	
}
