package dream.asset.std.product.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.std.product.dao.AssetStdProductListDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.service.AssetStdProductListService;

/**
 * 积魂前格 - 格废 serviceimpl
 * @author ghlee
 * @version
 * @since 1.0
 * 
 * @spring.bean id="assetStdProductListServiceTarget"
 * @spring.txbn id="assetStdProductListService"
 * @spring.property name="assetStdProductListDAO" ref="assetStdProductListDAO"
 */
public class AssetStdProductListServiceImpl implements AssetStdProductListService
{
    private AssetStdProductListDAO assetStdProductListDAO = null;

    public AssetStdProductListDAO getAssetStdProductListDAO() 
    {
		return assetStdProductListDAO;
	}

	public void setAssetStdProductListDAO(AssetStdProductListDAO assetStdProductListDAO) 
	{
		this.assetStdProductListDAO = assetStdProductListDAO;
	}

	public List findProductList(AssetStdProductCommonDTO assetStdProductCommonDTO,User user) throws Exception
    {     
        return assetStdProductListDAO.findProductList(assetStdProductCommonDTO,user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetStdProductListDAO.deleteProduct(id, user);
            }
        
        return result;
    }
	
    @Override
    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,User user) throws Exception
    {
        return assetStdProductListDAO.findTotalCount(assetStdProductCommonDTO, user);
    }
}

