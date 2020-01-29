package dream.asset.rpt.mtbfmttr.loc.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dao.AssetRptMtbfmttrLocListDAO;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocListService;

/**
 * MTBF,MTTR(위치) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMtbfmttrLocListServiceTarget"
 * @spring.txbn id="assetRptMtbfmttrLocListService"
 * @spring.property name="assetRptMtbfmttrLocListDAO" ref="assetRptMtbfmttrLocListDAO"
 */
public class AssetRptMtbfmttrLocListServiceImpl implements AssetRptMtbfmttrLocListService
{
    private AssetRptMtbfmttrLocListDAO assetRptMtbfmttrLocListDAO = null;
    
	public AssetRptMtbfmttrLocListDAO getAssetRptMtbfmttrLocListDAO()
    {
        return assetRptMtbfmttrLocListDAO;
    }
	
    public void setAssetRptMtbfmttrLocListDAO(
            AssetRptMtbfmttrLocListDAO assetRptMtbfmttrLocListDAO)
    {
        this.assetRptMtbfmttrLocListDAO = assetRptMtbfmttrLocListDAO;
    }
    
    public List findList(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser) throws Exception
    {
        return assetRptMtbfmttrLocListDAO.findList(assetRptMtbfmttrLocCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User user) throws Exception
    {
        return assetRptMtbfmttrLocListDAO.findTotalCount(assetRptMtbfmttrLocCommonDTO, user);
    }
	
}

