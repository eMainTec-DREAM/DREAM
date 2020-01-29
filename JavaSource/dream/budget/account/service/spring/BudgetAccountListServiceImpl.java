package dream.budget.account.service.spring;

import java.util.List;

import common.bean.User;
import dream.budget.account.dao.BudgetAccountListDAO;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.service.BudgetAccountListService;

/**
 * 예산계정 - 목록 serviceimpl
 * @author ghlee
 * @version
 * @since 1.0
 * 
 * @spring.bean id="budgetAccountListServiceTarget"
 * @spring.txbn id="budgetAccountListService"
 * @spring.property name="budgetAccountListDAO" ref="budgetAccountListDAO"
 */
public class BudgetAccountListServiceImpl implements BudgetAccountListService
{
    private BudgetAccountListDAO budgetAccountListDAO = null;

    public BudgetAccountListDAO getBudgetAccountListDAO() 
    {
		return budgetAccountListDAO;
	}

	public void setBudgetAccountListDAO(BudgetAccountListDAO budgetAccountListDAO) 
	{
		this.budgetAccountListDAO = budgetAccountListDAO;
	}

	public List findList(BudgetAccountCommonDTO budgetAccountCommonDTO,User user) throws Exception
    {     
        return budgetAccountListDAO.findList(budgetAccountCommonDTO,user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + budgetAccountListDAO.deleteList(id, user);
            }
        
        return result;
    }
	
    @Override
    public String findTotalCount(BudgetAccountCommonDTO budgetAccountCommonDTO,User user) throws Exception
    {
        return budgetAccountListDAO.findTotalCount(budgetAccountCommonDTO, user);
    }
}

