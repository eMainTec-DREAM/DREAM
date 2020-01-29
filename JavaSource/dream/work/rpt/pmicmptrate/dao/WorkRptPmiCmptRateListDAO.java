package dream.work.rpt.pmicmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;

/**
 * 예방점검항목 실행 비율 목록 - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmiCmptRateListDAO
{
    /**
     * FIND LIST
     * @param workRptPmiCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptPmiCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception;
    
}
