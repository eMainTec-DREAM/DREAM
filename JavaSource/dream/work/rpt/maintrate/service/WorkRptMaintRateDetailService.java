package dream.work.rpt.maintrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateDetailDTO;

/**
 * WorkRptMaintRate Page - Detail Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptMaintRateDetailService
{
    /**
     * PART FIND DETAIL
     * @param workRptMaintRateDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findPartDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception;
    /**
     * TYPE FIND DETAIL
     * @param workRptMaintRateDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findTypeDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception;
    
    /**
     * FIND DAY CHART
     * @param workRptMaintRateDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findDayChart(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception;
}
