package dream.work.rpt.pm.ratio.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;

/**
 * 계획보전율(위치) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmRatioListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO, User user);
    
}