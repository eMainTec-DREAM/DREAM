package dream.work.rpt.pmwrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;

/**
 * 주기정비 실행 비율 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptPmwRateListService
{
    /**
     * FIND LIST
     * @param workRptPmwRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception;
}
