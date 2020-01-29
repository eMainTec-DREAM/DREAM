package dream.budget.plan.list.service;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 예산관리 - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtBudgetListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    
    public String findTotalCount(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User user);

}
