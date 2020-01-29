package dream.budget.plan.list.service;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 부서별 예산금액  목록
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtDeptBgListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
    
    /**
     *  delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

}
