package dream.budget.plan.list.service;

import java.util.List;

import common.bean.User;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;

/**
 * 예산관리 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtBudgetDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPtBudgetDetailDTO findDetail(MaPtBudgetCommonDTO maPtBudgetCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtBudgetDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtBudgetDetailDTO maPtBudgetDetailDTO, User loginUser) throws Exception;

}
