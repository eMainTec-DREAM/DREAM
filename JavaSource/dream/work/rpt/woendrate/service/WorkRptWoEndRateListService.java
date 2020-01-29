package dream.work.rpt.woendrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;

/**
 * 작업오더 일마감 처리율 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptWoEndRateListService
{
    /**
     * FIND LIST
     * @param workRptWoEndRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoEndRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception;
}
