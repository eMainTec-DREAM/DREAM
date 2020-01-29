package dream.budget.account.dao;

import common.bean.User;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;

/**
 * 예산계정 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:  $
 * @since 1.0
 */
public interface BudgetAccountDetailDAO
{
    public BudgetAccountDetailDTO findDetail(BudgetAccountCommonDTO budgetAccountCommonDTO, User user);
    
    public int insertDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user);
    
    public int updateDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user);
    
    public String validAccountNo(BudgetAccountDetailDTO budgetAccountDetailDTO, User user);
}