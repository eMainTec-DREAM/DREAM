package dream.asset.std.asset.service.spring;

import common.bean.User;
import dream.asset.std.asset.dao.AssetStdAssetDetailDAO;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;
import dream.asset.std.asset.service.AssetStdAssetDetailService;

/**
 * 회계자산 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="assetStdAssetDetailServiceTarget"
 * @spring.txbn id="assetStdAssetDetailService"
 * @spring.property name="assetStdAssetDetailDAO" ref="assetStdAssetDetailDAO"
 */
public class AssetStdAssetDetailServiceImpl implements AssetStdAssetDetailService
{
    private AssetStdAssetDetailDAO assetStdAssetDetailDAO = null;
    
    public AssetStdAssetDetailDAO getAssetStdAssetDetailDAO() {
		return assetStdAssetDetailDAO;
	}

	public void setAssetStdAssetDetailDAO(AssetStdAssetDetailDAO assetStdAssetDetailDAO) {
		this.assetStdAssetDetailDAO = assetStdAssetDetailDAO;
	}

	public AssetStdAssetDetailDTO findDetail(User user, String assetNo)throws Exception
    {
        return assetStdAssetDetailDAO.findDetail(user, assetNo);
    }
    
	public int insertDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception
    {        
        return assetStdAssetDetailDAO.insertDetail(assetStdAssetDetailDTO, user);
    }
	
	public int updateDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception
    {        
        return assetStdAssetDetailDAO.updateDetail(assetStdAssetDetailDTO, user);
    }
	
	public String validAssetNo(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception
    {
        return assetStdAssetDetailDAO.validAssetNo(assetStdAssetDetailDTO, user);
    }
}
