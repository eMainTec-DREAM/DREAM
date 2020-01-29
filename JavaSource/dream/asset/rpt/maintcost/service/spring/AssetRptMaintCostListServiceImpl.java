package dream.asset.rpt.maintcost.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.maintcost.dao.AssetRptMaintCostListDAO;
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;
import dream.asset.rpt.maintcost.service.AssetRptMaintCostListService;

/**
 * 수선유지비 집행현황 목록 - List Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="assetRptMaintCostListServiceTarget"
 * @spring.txbn id="assetRptMaintCostListService"
 * @spring.property name="assetRptMaintCostListDAO" ref="assetRptMaintCostListDAO"
 */
public class AssetRptMaintCostListServiceImpl implements AssetRptMaintCostListService
{
    private AssetRptMaintCostListDAO assetRptMaintCostListDAO = null;

    public List findList(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception
    {      
        return assetRptMaintCostListDAO.findList(assetRptMaintCostCommonDTO,user);
    }

    public AssetRptMaintCostListDAO getAssetRptMaintCostListDAO() {
        return assetRptMaintCostListDAO;
    }

    public void setAssetRptMaintCostListDAO(AssetRptMaintCostListDAO assetRptMaintCostListDAO) {
        this.assetRptMaintCostListDAO = assetRptMaintCostListDAO;
    }    
    
    public String findTotalCount(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO,User user)  throws Exception
    {
        return assetRptMaintCostListDAO.findTotalCount(assetRptMaintCostCommonDTO, user);
    }
}
