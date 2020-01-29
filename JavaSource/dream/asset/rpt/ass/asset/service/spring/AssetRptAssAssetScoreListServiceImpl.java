package dream.asset.rpt.ass.asset.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.ass.asset.dao.AssetRptAssAssetScoreListDAO;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;
import dream.asset.rpt.ass.asset.service.AssetRptAssAssetScoreListService;

/**
 * AssetRptAssAssetScoreList Page - List Service implements
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListServiceImpl.java,v 1.0 2018/08/23 15:17:40 nhkim8548 Exp $
 * @since 1.0
 * @spring.bean id="assetRptAssAssetScoreListServiceTarget"
 * @spring.txbn id="assetRptAssAssetScoreListService"
 * @spring.property name="assetRptAssAssetScoreListDAO" ref="assetRptAssAssetScoreListDAO"
 *  */
public class AssetRptAssAssetScoreListServiceImpl implements AssetRptAssAssetScoreListService
{
    private AssetRptAssAssetScoreListDAO assetRptAssAssetScoreListDAO = null;
    private AssetRptAssAssetScoreListService assetRptAssAssetScoreDetailService = null;
    
    public AssetRptAssAssetScoreListDAO getAssetRptAssAssetScoreListDAO()
    {
        return assetRptAssAssetScoreListDAO;
    }
    public void setAssetRptAssAssetScoreListDAO(
            AssetRptAssAssetScoreListDAO assetRptAssAssetScoreListDAO)
    {
        this.assetRptAssAssetScoreListDAO = assetRptAssAssetScoreListDAO;
    }
    public AssetRptAssAssetScoreListService getAssetRptAssAssetScoreDetailService()
    {
        return assetRptAssAssetScoreDetailService;
    }
    public void setAssetRptAssAssetScoreDetailService(
            AssetRptAssAssetScoreListService assetRptAssAssetScoreDetailService)
    {
        this.assetRptAssAssetScoreDetailService = assetRptAssAssetScoreDetailService;
    }
    
    @Override
    public List findList(
            AssetRptAssAssetScoreCommonDTO AssetRptAssAssetScoreCommonDTO, User user) throws Exception
    {
        return assetRptAssAssetScoreListDAO.findList(AssetRptAssAssetScoreCommonDTO, user);
    }
    @Override
    public String findTotalCount( AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO, User user) throws Exception
    {
        return assetRptAssAssetScoreListDAO.findTotalCount(assetRptAssAssetScoreCommonDTO, user);
    }
    
	
}
