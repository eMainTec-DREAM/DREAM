package dream.mgr.audtrail.service;

import java.util.List;

import common.bean.User;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;


/**
 * Audit Trail service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrAudTrailListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param mgrAudTrailListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user);    
    
    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception;
}
