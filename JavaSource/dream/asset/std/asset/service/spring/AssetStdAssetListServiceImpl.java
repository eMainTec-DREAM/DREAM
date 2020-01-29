package dream.asset.std.asset.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.std.asset.dao.AssetStdAssetListDAO;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;
import dream.asset.std.asset.service.AssetStdAssetListService;

/**
 * 회계자산 - 목록 serviceimpl
 * @author ghlee
 * @version
 * @since 1.0
 * 
 * @spring.bean id="assetStdAssetListServiceTarget"
 * @spring.txbn id="assetStdAssetListService"
 * @spring.property name="assetStdAssetListDAO" ref="assetStdAssetListDAO"
 */
public class AssetStdAssetListServiceImpl implements AssetStdAssetListService
{
    private AssetStdAssetListDAO assetStdAssetListDAO = null;

    public AssetStdAssetListDAO getAssetStdAssetListDAO() 
    {
		return assetStdAssetListDAO;
	}

	public void setAssetStdAssetListDAO(AssetStdAssetListDAO assetStdAssetListDAO) 
	{
		this.assetStdAssetListDAO = assetStdAssetListDAO;
	}

	public List findAssetList(AssetStdAssetCommonDTO assetStdAssetCommonDTO,User user) throws Exception
    {     
        return assetStdAssetListDAO.findAssetList(assetStdAssetCommonDTO,user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetStdAssetListDAO.deleteAsset(id, user);
            }
        
        return result;
    }
	
    @Override
    public String findTotalCount(AssetStdAssetCommonDTO assetStdAssetCommonDTO,User user) throws Exception
    {
        return assetStdAssetListDAO.findTotalCount(assetStdAssetCommonDTO, user);
    }
}

