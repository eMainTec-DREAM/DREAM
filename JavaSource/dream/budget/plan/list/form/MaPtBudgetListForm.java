package dream.budget.plan.list.form;

import common.struts.BaseForm;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * ������� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtBudgetListForm"
 */
public class MaPtBudgetListForm extends BaseForm
{    
    /** ���� */
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
