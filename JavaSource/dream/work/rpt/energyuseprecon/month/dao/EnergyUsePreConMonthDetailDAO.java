package dream.work.rpt.energyuseprecon.month.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;

/**
 * EnergyUsePreConMonth Page - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface EnergyUsePreConMonthDetailDAO
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
     * FIND TOTAL LIST
     * @param energyUsePreConMonthDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO, User user) throws Exception;
    
}
