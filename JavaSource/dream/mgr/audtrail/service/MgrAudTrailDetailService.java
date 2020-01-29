package dream.mgr.audtrail.service;

import common.bean.User;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrAudTrailDetailService
{   
	/**
     * find detail
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAudTrailDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrAudTrailDetailDTO findDetail(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception;
}
