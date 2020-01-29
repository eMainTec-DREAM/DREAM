package dream.asset.rpt.nyearpo.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;

/**
 * N Year Spare Part ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptNYearPOListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptNYearPOCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User loginUser);
    
    public String findTotalCount(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User user);
    
}