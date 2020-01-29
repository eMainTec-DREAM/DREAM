package dream.work.rpt.pmiequipplanrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;

/**
 * 고장TOP(위치) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmiEquipPlanDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanDetailDTO
     * @param user
     * @throws Exception
     */
    public List findDetail(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser)  throws Exception;

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
    public String findTotalCount(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User user) throws Exception;
}
