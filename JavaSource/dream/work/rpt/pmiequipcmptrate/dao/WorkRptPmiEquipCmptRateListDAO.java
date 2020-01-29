package dream.work.rpt.pmiequipcmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;

/**
 * 예방점검설비 실행 비율 목록 - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmiEquipCmptRateListDAO
{
    /**
     * FIND LIST
     * @param workRptPmiEquipCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptPmiEquipCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception;
    
}
