package dream.work.rpt.pm.ratio.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;

/**
 * 계획보전율(위치) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmRatioListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User user);
    
}
