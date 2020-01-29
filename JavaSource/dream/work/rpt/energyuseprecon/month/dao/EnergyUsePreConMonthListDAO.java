package dream.work.rpt.energyuseprecon.month.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;

/**
 * EnergyUsePreConMonth Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface EnergyUsePreConMonthListDAO
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
     * FIND TOTAL LIST
     * @param energyUsePreConMonthCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO, User user) throws Exception;
    
}
