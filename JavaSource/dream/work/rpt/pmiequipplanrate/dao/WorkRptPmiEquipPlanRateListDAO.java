package dream.work.rpt.pmiequipplanrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;

/**
 * 예방점검 설비 계획대비 실행 비율 목록 - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmiEquipPlanRateListDAO
{
    /**
     * FIND LIST
     * @param workRptPmiEquipPlanRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptPmiEquipPlanRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception;
    
}
