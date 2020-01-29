package dream.asset.rpt.bdintensity.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;

/**
 * 설비별 고장강도율 목록 - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface AssetRptBdIntensityListService
{
    /**
     * FIND LIST
     * @param assetRptBdIntensityCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptBdIntensityCommonDTO
     * @return
     */
    public String findTotalCount(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
}
