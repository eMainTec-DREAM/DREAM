package dream.asset.std.product.service.spring;

import common.bean.User;
import dream.asset.std.product.dao.AssetStdProductEquipDetailDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
import dream.asset.std.product.service.AssetStdProductEquipDetailService;

/**
 * 생산설비 - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="assetStdProductEquipDetailServiceTarget"
 * @spring.txbn id="assetStdProductEquipDetailService"
 * @spring.property name="assetStdProductEquipDetailDAO" ref="assetStdProductEquipDetailDAO"
 */
public class AssetStdProductEquipDetailServiceImpl implements AssetStdProductEquipDetailService
{
    private AssetStdProductEquipDetailDAO assetStdProductEquipDetailDAO = null;
    
    public AssetStdProductEquipDetailDTO findDetail(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception
    {
    	return assetStdProductEquipDetailDAO.findDetail(assetStdProductCommonDTO,assetStdProductEquipListDTO, user);
    }
    
    public int insertDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user) throws Exception
    {
     	return assetStdProductEquipDetailDAO.insertDetail(assetStdProductCommonDTO,assetStdProductEquipDetailDTO, user);
    }
    
    public int updateDetail(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, User user) throws Exception
    {
     	return assetStdProductEquipDetailDAO.updateDetail(assetStdProductCommonDTO,assetStdProductEquipDetailDTO, user);
    }

	public AssetStdProductEquipDetailDAO getAssetStdProductEquipDetailDAO() {
		return assetStdProductEquipDetailDAO;
	}

	public void setAssetStdProductEquipDetailDAO(AssetStdProductEquipDetailDAO assetStdProductEquipDetailDAO) {
		this.assetStdProductEquipDetailDAO = assetStdProductEquipDetailDAO;
	}
    

}
