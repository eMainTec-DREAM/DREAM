package dream.budget.account.service.spring;

import common.bean.User;
import dream.budget.account.dao.BudgetAccountDetailDAO;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;
import dream.budget.account.service.BudgetAccountDetailService;

/**
 * 예산계정 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="budgetAccountDetailServiceTarget"
 * @spring.txbn id="budgetAccountDetailService"
 * @spring.property name="budgetAccountDetailDAO" ref="budgetAccountDetailDAO"
 */
public class BudgetAccountDetailServiceImpl implements BudgetAccountDetailService
{
    private BudgetAccountDetailDAO budgetAccountDetailDAO = null;
    
    public BudgetAccountDetailDAO getBudgetAccountDetailDAO() {
		return budgetAccountDetailDAO;
	}

	public void setBudgetAccountDetailDAO(BudgetAccountDetailDAO budgetAccountDetailDAO) {
		this.budgetAccountDetailDAO = budgetAccountDetailDAO;
	}

	public BudgetAccountDetailDTO findDetail(BudgetAccountCommonDTO budgetAccountCommonDTO, User user)throws Exception
    {
        return budgetAccountDetailDAO.findDetail(budgetAccountCommonDTO, user);
    }
    
	public int insertDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception
    {        
        return budgetAccountDetailDAO.insertDetail(budgetAccountDetailDTO, user);
    }
	
	public int updateDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception
    {        
        return budgetAccountDetailDAO.updateDetail(budgetAccountDetailDTO, user);
    }
	
	public String validAccountNo(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception
    {
        return budgetAccountDetailDAO.validAccountNo(budgetAccountDetailDTO, user);
    }
}
