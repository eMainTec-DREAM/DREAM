package dream.mgr.audtrail.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDtlListDTO;

/**
 * MgrAudTrailDtl Page - List Service
 * @author youngjoo38
 * @version $Id: MgrAudTrailDtlListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface MgrAudTrailDtlListService
{
    /**
     * FIND ASS ASSET SCORE LIST
     * @param mgrAudTrailDtlListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrAudTrailDtlListDTO
     * @return
     */
    public String findTotalCount(MgrAudTrailCommonDTO mgrAudTrailCommonDTO, MgrAudTrailDtlListDTO mgrAudTrailDtlListDTO, User user) throws Exception;
}