package dream.work.rpt.maintrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;

/**
 * WorkRptMaintRate Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptMaintRateListDAO
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
     * FIND TOTAL LIST
     * @param workRptMaintRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) throws Exception;
    
}
