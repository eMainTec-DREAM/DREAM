package dream.asset.rpt.eqpartprecon.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConDetailDTO;

/**
 * AssetRptEqPartPreCon Page - Detail Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptEqPartPreConDetailService
{
    /**
     * PART FIND DETAIL
     * @param assetRptEqPartPreConDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findPartDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception;
    /**
     * STOCK FIND DETAIL
     * @param assetRptEqPartPreConDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findStockDetail(AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO, User user) throws Exception;
    
}
