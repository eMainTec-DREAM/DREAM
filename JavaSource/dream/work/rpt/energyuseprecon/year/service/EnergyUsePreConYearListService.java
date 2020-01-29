package dream.work.rpt.energyuseprecon.year.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;

/**
 * EnergyUsePreConYear Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface EnergyUsePreConYearListService
{
    /**
     * FIND LIST
     * @param energyUsePreConYearCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param energyUsePreConYearCommonDTO
     * @return
     */
    public String findTotalCount(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) throws Exception;
}
