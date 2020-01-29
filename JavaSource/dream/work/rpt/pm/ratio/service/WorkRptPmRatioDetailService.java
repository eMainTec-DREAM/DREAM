package dream.work.rpt.pm.ratio.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;

/**
 * 계획보전율(위치) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPmRatioDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmRatioDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO, User loginUser);
    
}
