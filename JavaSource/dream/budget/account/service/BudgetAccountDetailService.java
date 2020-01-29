package dream.budget.account.service;

import common.bean.User;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;

/**
 * 예산계정 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface BudgetAccountDetailService
{    
    public BudgetAccountDetailDTO findDetail(BudgetAccountCommonDTO budgetAccountCommonDTO, User user)throws Exception;
    
    public int insertDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception;
    
    public int updateDetail(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception;
    
    public String validAccountNo(BudgetAccountDetailDTO budgetAccountDetailDTO, User user) throws Exception;
}
