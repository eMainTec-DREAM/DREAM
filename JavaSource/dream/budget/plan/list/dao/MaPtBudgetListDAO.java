package dream.budget.plan.list.dao;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 抗魂包府 - 格废 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPtBudgetListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @return List
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
    
    /**
     * 昏力
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteHeader(String key, User loginUser);
    
    public int deleteDetail(String key, User loginUser);
    
    public String findTotalCount(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User user);

}