package dream.work.rpt.pm.ratio.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;

/**
 * 계획보전율(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmRatioDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO, User loginUser);
    
}