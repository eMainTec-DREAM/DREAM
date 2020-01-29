package dream.work.rpt.pmins.ach.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;

/**
 * 예방점검 이행율(담당자) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsAchListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsAchCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User user);
    
}
