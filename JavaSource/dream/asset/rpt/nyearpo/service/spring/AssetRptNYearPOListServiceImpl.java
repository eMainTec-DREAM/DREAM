package dream.asset.rpt.nyearpo.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.nyearpo.dao.AssetRptNYearPOListDAO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.service.AssetRptNYearPOListService;

/**
 * N Year Spare Part ¸ñ·Ï
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptNYearPOListServiceTarget"
 * @spring.txbn id="assetRptNYearPOListService"
 * @spring.property name="assetRptNYearPOListDAO" ref="assetRptNYearPOListDAO"
 */
public class AssetRptNYearPOListServiceImpl implements AssetRptNYearPOListService
{
    private AssetRptNYearPOListDAO assetRptNYearPOListDAO = null;
    
	public AssetRptNYearPOListDAO getAssetRptNYearPOListDAO()
    {
        return assetRptNYearPOListDAO;
    }
	
    public void setAssetRptNYearPOListDAO(
            AssetRptNYearPOListDAO assetRptNYearPOListDAO)
    {
        this.assetRptNYearPOListDAO = assetRptNYearPOListDAO;
    }
    
    public List findList(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User loginUser)
    {
    	List result = null;
		result = assetRptNYearPOListDAO.findList(assetRptNYearPOCommonDTO, loginUser);
    	return result;
    }

    @Override
    public String findTotalCount(AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO, User user)
    {
        return assetRptNYearPOListDAO.findTotalCount(assetRptNYearPOCommonDTO, user);
    }
	
}

