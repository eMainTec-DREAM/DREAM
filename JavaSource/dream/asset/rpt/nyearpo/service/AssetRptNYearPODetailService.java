package dream.asset.rpt.nyearpo.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPODetailDTO;

/**
 * N Year Spare Part 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptNYearPODetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param assetRptNYearPOCommonDTO 
     * @since   1.0
     * 
     * @param assetRptNYearPODetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, AssetRptNYearPODetailDTO assetRptNYearPODetailDTO, User loginUser);
    
}
