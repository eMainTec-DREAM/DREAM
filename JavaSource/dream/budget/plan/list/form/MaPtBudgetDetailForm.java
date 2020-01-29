package dream.budget.plan.list.form;

import common.struts.BaseForm;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;

/**
 * ������� - �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maPtBudgetDetailForm"
 */
public class MaPtBudgetDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaPtBudgetCommonDTO maPtBudgetCommonDTO = new MaPtBudgetCommonDTO();
    //========================================================================
    /** �� */
    private MaPtBudgetDetailDTO maPtBudgetDetailDTO = new MaPtBudgetDetailDTO();


    public MaPtBudgetCommonDTO getMaPtBudgetCommonDTO() {
		return maPtBudgetCommonDTO;
	}

	public void setMaPtBudgetCommonDTO(MaPtBudgetCommonDTO maPtBudgetCommonDTO) 
	{
		this.maPtBudgetCommonDTO = maPtBudgetCommonDTO;
	}

	public MaPtBudgetDetailDTO getMaPtBudgetDetailDTO() {
		return maPtBudgetDetailDTO;
	}

	public void setMaPtBudgetDetailDTO(MaPtBudgetDetailDTO maPtBudgetDetailDTO) 
	{
		this.maPtBudgetDetailDTO = maPtBudgetDetailDTO;
	}
}
