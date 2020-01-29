package dream.budget.plan.list.dao;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;

/**
 * 何前芭贰贸 格废 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtDeptBgListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return List
     */
    public List findList(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptVendorId
     * @return
     */
    public int deleteList(String ptVendorId, User loginUser);
}