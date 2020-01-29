package dream.work.rpt.deptworkprecon.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;

/**
 * 부서별 작업진행현황 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */	
public interface WorkRptDeptWorkPreconListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param workRptDeptWorkPreconListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser);    
    
    public String findWoTypes(User user);
}
