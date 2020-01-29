package dream.work.rpt.pmins.deptach.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;

/**
 * 예방점검 이행율(부서) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsDeptAchListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsDeptAchCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User loginUser);

    public String findTotalCount(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User user);
    
}
