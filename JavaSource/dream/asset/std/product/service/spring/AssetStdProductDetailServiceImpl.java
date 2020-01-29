package dream.asset.std.product.service.spring;

import common.bean.User;
import dream.asset.std.product.dao.AssetStdProductDetailDAO;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;
import dream.asset.std.product.service.AssetStdProductDetailService;

/**
 * 积魂前格 - 惑技 serviceimpl 
 * @author  ghlee
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="assetStdProductDetailServiceTarget"
 * @spring.txbn id="assetStdProductDetailService"
 * @spring.property name="assetStdProductDetailDAO" ref="assetStdProductDetailDAO"
 */
public class AssetStdProductDetailServiceImpl implements AssetStdProductDetailService
{
    private AssetStdProductDetailDAO assetStdProductDetailDAO = null;
    
    public AssetStdProductDetailDAO getAssetStdProductDetailDAO() {
		return assetStdProductDetailDAO;
	}

	public void setAssetStdProductDetailDAO(AssetStdProductDetailDAO assetStdProductDetailDAO) {
		this.assetStdProductDetailDAO = assetStdProductDetailDAO;
	}

	public AssetStdProductDetailDTO findDetail(User user, String assetNo)throws Exception
    {
        return assetStdProductDetailDAO.findDetail(user, assetNo);
    }
    
	public int insertDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception
    {        
        return assetStdProductDetailDAO.insertDetail(assetStdProductDetailDTO, user);
    }
	
	public int updateDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception
    {        
        return assetStdProductDetailDAO.updateDetail(assetStdProductDetailDTO, user);
    }
	
	public String validProductNo(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception
    {
        return assetStdProductDetailDAO.validProductNo(assetStdProductDetailDTO, user);
    }
}
