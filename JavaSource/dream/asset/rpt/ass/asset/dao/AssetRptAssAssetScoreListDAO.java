package dream.asset.rpt.ass.asset.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;

/**
 * AssetRptAssAssetScore Page - List DAO
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListDAO.java,v 1.0 2018/08/03 12:26:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public interface AssetRptAssAssetScoreListDAO
{
    /**
     * FIND LIST
     * @param assetRptAssAssetScoreCommon
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception;
    /**
     * TOTALCOUNT
     * @param assetRptAssAssetScoreCommon
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception;
}
