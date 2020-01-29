package dream.work.rpt.deptworkprecon.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;

/**
 * �μ��� �۾�������Ȳ service
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
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO, User loginUser);    
    
    public String findWoTypes(User user);
}
