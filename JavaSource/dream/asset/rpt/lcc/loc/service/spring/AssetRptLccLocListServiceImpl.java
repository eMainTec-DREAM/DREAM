package dream.asset.rpt.lcc.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.loc.dao.AssetRptLccLocListDAO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;
import dream.asset.rpt.lcc.loc.service.AssetRptLccLocListService;

/**
 * 고장TOP(위치) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptLccLocListServiceTarget"
 * @spring.txbn id="assetRptLccLocListService"
 * @spring.property name="assetRptLccLocListDAO" ref="assetRptLccLocListDAO"
 */
public class AssetRptLccLocListServiceImpl implements AssetRptLccLocListService
{
    private AssetRptLccLocListDAO assetRptLccLocListDAO = null;
    
	public AssetRptLccLocListDAO getAssetRptLccLocListDAO()
    {
        return assetRptLccLocListDAO;
    }
	
    public void setAssetRptLccLocListDAO(
            AssetRptLccLocListDAO assetRptLccLocListDAO)
    {
        this.assetRptLccLocListDAO = assetRptLccLocListDAO;
    }
    
    public List findList(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User loginUser)
    {
        return assetRptLccLocListDAO.findList(assetRptLccLocCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User user)
    {
        return assetRptLccLocListDAO.findTotalCount(assetRptLccLocCommonDTO, user);
    }
	
}

