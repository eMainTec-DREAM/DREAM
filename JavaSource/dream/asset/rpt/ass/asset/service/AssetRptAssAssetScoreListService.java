package dream.asset.rpt.ass.asset.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;

/**
 * AssetRptAssAssetScore Page - List Service
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListService.java,v 1.0 2018/08/23 15:07:40 nhkim8548 Exp $
 * @since 1.0
 */
public interface AssetRptAssAssetScoreListService
{
    /**
     * FIND LIST
     * @author  nhkim8548
     * @param AssetRptAssAssetScoreCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetRptAssAssetScoreCommonDTO AssetRptAssAssetScoreCommonDTO, User user) throws Exception;
    /**
     * find Total Count
     * @author  nhkim8548
     * @param assetRptAssAssetScoreCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception;
}
