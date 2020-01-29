package dream.work.rpt.pmiequipplanrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;

/**
 * �������� ���� ��ȹ��� ���� ���� ��� - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptPmiEquipPlanRateListService
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
     * find Total Count
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception;
}
