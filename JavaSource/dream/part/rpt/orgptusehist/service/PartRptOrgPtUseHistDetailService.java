package dream.part.rpt.orgptusehist.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistDetailDTO;

/**
 * PartRptOrgPtUseHist Page - Detail Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface PartRptOrgPtUseHistDetailService
{
    /**
     * FIND DETAIL
     * @param partRptOrgPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findDetail(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception;
    
    /**
     * FIND CHART
     * @param partRptOrgPtUseHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findChart(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception;
}
