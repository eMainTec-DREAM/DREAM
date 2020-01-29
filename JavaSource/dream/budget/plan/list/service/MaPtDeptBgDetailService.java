package dream.budget.plan.list.service;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;

/**
 * 부서별예산금액 상세
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtDeptBgDetailService
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
     * @throws Exception
     */
    public MaPtDeptBgDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser) throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtDeptBgDetailDTO maPtDeptBgDetailDTO, User loginUser) throws Exception;
}
