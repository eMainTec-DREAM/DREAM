package dream.work.rpt.pmins.deptach.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;

/**
 * 예방점검 이행율(부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkRptPminsDeptAchDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$   
     * @since   1.0
     * 
     * @param workRptPminsDeptAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User loginUser);
    
    public String findTotalCount(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User user);
}