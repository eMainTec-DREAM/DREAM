package dream.budget.plan.list.dao;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;

/**
 * 何前芭贰贸 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtDeptBgDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtDeptBgDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @return
     */
    public int updateVendor(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @return
     */
    public int insertPtVendor(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser);

	public MaPtDeptBgDetailDTO findNewDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
}