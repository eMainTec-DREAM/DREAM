package dream.budget.account.service;

import java.util.List;

import common.bean.User;
import dream.budget.account.dto.BudgetAccountCommonDTO;

/**
 * 예산계정 - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BudgetAccountListService
{     
    public List findList(BudgetAccountCommonDTO budgetAccountCommonDTO, User user) throws Exception;    
    
    public int deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(BudgetAccountCommonDTO budgetAccountCommonDTO,User user) throws Exception;
}
