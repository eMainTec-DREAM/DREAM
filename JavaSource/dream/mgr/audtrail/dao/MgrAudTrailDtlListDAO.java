package dream.mgr.audtrail.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;

/**
 * MgrAudTrailDtl Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrAudTrailDtlListDAO
{
    /**
     * FIND LIST
     * @param mgrAudTrailDtlListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param mgrAudTrailDtlListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception;
    
    public String findPrevKeyId(MgrAudTrailCommonDTO mgrAudTrailCommonDTO , User user);
}
