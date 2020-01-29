package dream.asset.rpt.eqpartprecon.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;

/**
 * AssetRptEqPartPreCon Page - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptEqPartPreConListService
{
    /**
     * FIND LIST
     * @param assetRptEqPartPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptEqPartPreConCommonDTO
     * @return
     */
    public String findTotalCount(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception;
}
