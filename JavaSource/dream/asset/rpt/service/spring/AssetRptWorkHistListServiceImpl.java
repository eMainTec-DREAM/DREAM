package dream.asset.rpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.dao.AssetRptWorkHistListDAO;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.service.AssetRptWorkHistListService;

/**
 * 설비이력(과거) - List Service implements
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="assetRptWorkHistListServiceTarget"
 * @spring.txbn id="assetRptWorkHistListService"
 * @spring.property name="assetRptWorkHistListDAO" ref="assetRptWorkHistListDAO"
 */
public class AssetRptWorkHistListServiceImpl implements AssetRptWorkHistListService
{
	private AssetRptWorkHistListDAO assetRptWorkHistListDAO = null;

	public List findRptWorkHistList(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception
    {      
        return assetRptWorkHistListDAO.findRptWorkHistList(assetRptWorkHistCommonDTO,user);
    }

	public int deleteRptWorkHistList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetRptWorkHistListDAO.deleteRptWorkHistList(id, user);
            }
        return result;
    }

	public AssetRptWorkHistListDAO getAssetRptWorkHistListDAO() {
		return assetRptWorkHistListDAO;
	}

	public void setAssetRptWorkHistListDAO(AssetRptWorkHistListDAO assetRptWorkHistListDAO) {
		this.assetRptWorkHistListDAO = assetRptWorkHistListDAO;
	}
	public String findTotalCount(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO,User user) throws Exception
    {
        return assetRptWorkHistListDAO.findTotalCount(assetRptWorkHistCommonDTO, user);
    }
}

