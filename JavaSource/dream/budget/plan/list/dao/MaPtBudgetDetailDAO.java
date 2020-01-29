package dream.budget.plan.list.dao;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;

/**
 * 예산관리 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaPtBudgetDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaPtBudgetDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @return
     */
    public int insertDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @return
     */
    public int updateDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser);
}