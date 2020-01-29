package dream.asset.rpt.service.spring;

import common.bean.User;
import dream.asset.rpt.dao.AssetRptWorkHistDetailDAO;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;
import dream.asset.rpt.service.AssetRptWorkHistDetailService;

/**
 * 설비이력(과거) - Detail Service implements
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="assetRptWorkHistDetailServiceTarget"
 * @spring.txbn id="assetRptWorkHistDetailService"
 * @spring.property name="assetRptWorkHistDetailDAO" ref="assetRptWorkHistDetailDAO"
 */
public class AssetRptWorkHistDetailServiceImpl implements AssetRptWorkHistDetailService
{
    private AssetRptWorkHistDetailDAO assetRptWorkHistDetailDAO = null;
    
    public AssetRptWorkHistDetailDTO findRptWorkHistDetail(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception
    {
    	return assetRptWorkHistDetailDAO.findRptWorkHistDetail(assetRptWorkHistCommonDTO, user);
    }
    
    public int insertRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception
    {
    	return assetRptWorkHistDetailDAO.insertRptWorkHistDetail(assetRptWorkHistDetailDTO, user);
    }
    
    public int updateRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception
    {
    	 return assetRptWorkHistDetailDAO.updateRptWorkHistDetail(assetRptWorkHistDetailDTO, user);
    }

	public AssetRptWorkHistDetailDAO getAssetRptWorkHistDetailDAO() {
		return assetRptWorkHistDetailDAO;
	}

	public void setAssetRptWorkHistDetailDAO(AssetRptWorkHistDetailDAO assetRptWorkHistDetailDAO) {
		this.assetRptWorkHistDetailDAO = assetRptWorkHistDetailDAO;
	}
}
