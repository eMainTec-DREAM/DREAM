package dream.asset.rpt.nyearpo.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;

/**
 * N Year Spare Part ¸ñ·Ï
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptNYearPOListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptNYearPOCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User loginUser);

    public String findTotalCount(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User user);
    
}
