package dream.work.rpt.pmiequipplanrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmiEquipPlanDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanDetailDTO
     * @param user
     * @return List
     */
    public List findDetail(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser)throws Exception;
    
    /**
     * find Total Count
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanDetailDTO
     * @param user
     * @return
     */
    public String findTotalCount(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser)throws Exception;
    
}