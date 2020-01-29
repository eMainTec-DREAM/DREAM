package dream.work.rpt.deptworkprecon.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;

/**
 * 부서별 작업진행현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkRptDeptWorkPreconListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workRptDeptWorkPreconListDTO
     * @return List
     */
    public List findList(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser);
 
    public List findWoTypes(User user);
}