package dream.work.rpt.energyuseprecon.month.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;

/**
 * EnergyUsePreConMonth Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface EnergyUsePreConMonthListService
{
    /**
     * FIND LIST
     * @param energyUsePreConMonthCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConMonthCommonDTO
     * @return
     */
    public String findTotalCount(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) throws Exception;
}
