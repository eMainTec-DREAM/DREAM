package dream.work.rpt.energyuseprecon.year.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;

/**
 * EnergyUsePreConYear Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface EnergyUsePreConYearListDAO
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
     * FIND TOTAL LIST
     * @param energyUsePreConYearCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO, User user) throws Exception;
    
}
