package dream.work.rpt.pmins.ach.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;

/**
 * 예방점검 이행율(담당자) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsAchDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsAchDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User user);
}
