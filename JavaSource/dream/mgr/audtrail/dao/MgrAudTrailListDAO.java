package dream.mgr.audtrail.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;


/**
 * Audit Trail DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrAudTrailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAudTrailListDTO
     * @return List
     */
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user);
 
    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, User user) throws Exception;
}