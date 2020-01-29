package dream.asset.rpt.bdintensity.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;

/**
 * 설비별 고장강도율 목록 - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetRptBdIntensityListDAO
{
    /**
     * FIND LIST
     * @param assetRptBdIntensityCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findPlantList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    public List findEqLocList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    public List findUsageDeptList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    public List findEqCtgList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    public List findEqpList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param assetRptBdIntensityCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception;
    
}
