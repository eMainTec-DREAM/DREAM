package dream.work.rpt.maintrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;

/**
 * WorkRptMaintRate Page - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptMaintRateListService
{
    /**
     * FIND LIST
     * @param workRptMaintRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptMaintRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) throws Exception;
}
