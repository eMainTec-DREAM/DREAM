package dream.part.rpt.orgptusehist.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;

/**
 * PartRptOrgPtUseHist Page - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface PartRptOrgPtUseHistListService
{
    /**
     * FIND LIST
     * @param partRptOrgPtUseHistCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param partRptOrgPtUseHistCommonDTO
     * @return
     */
    public String findTotalCount(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) throws Exception;
}
