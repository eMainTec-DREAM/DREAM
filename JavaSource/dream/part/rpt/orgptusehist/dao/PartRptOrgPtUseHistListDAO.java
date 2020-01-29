package dream.part.rpt.orgptusehist.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;

/**
 * PartRptOrgPtUseHist Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartRptOrgPtUseHistListDAO
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
     * FIND TOTAL LIST
     * @param partRptOrgPtUseHistCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) throws Exception;
    
}
