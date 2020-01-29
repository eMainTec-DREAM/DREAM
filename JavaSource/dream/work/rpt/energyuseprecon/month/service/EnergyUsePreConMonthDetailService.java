package dream.work.rpt.energyuseprecon.month.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;

/**
 * EnergyUsePreConMonth Page - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface EnergyUsePreConMonthDetailService
{
    /**
     * FIND DETAIL
     * @param energyUsePreConMonthDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findDetail(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConMonthDetailDTO
     * @return
     */
    public String findTotalCount(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) throws Exception;
}
