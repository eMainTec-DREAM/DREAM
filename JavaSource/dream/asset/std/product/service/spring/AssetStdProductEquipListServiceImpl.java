package dream.asset.std.product.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.std.product.dao.AssetStdProductEquipDetailDAO;
import dream.asset.std.product.dao.AssetStdProductEquipListDAO;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
import dream.asset.std.product.service.AssetStdProductEquipListService;

/**
 * 생산설비 - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="assetStdProductEquipListServiceTarget"
 * @spring.txbn id="assetStdProductEquipListService"
 * @spring.property name="assetStdProductEquipListDAO" ref="assetStdProductEquipListDAO"
 * @spring.property name="assetStdProductEquipDetailDAO" ref="assetStdProductEquipDetailDAO"
 */
public class AssetStdProductEquipListServiceImpl implements AssetStdProductEquipListService
{
	private AssetStdProductEquipListDAO assetStdProductEquipListDAO = null;
	private AssetStdProductEquipDetailDAO assetStdProductEquipDetailDAO = null;

	public List findList(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception
    {      
        return assetStdProductEquipListDAO.findList(assetStdProductCommonDTO,assetStdProductEquipListDTO,user);
    }

	public AssetStdProductEquipDetailDAO getAssetStdProductEquipDetailDAO() {
		return assetStdProductEquipDetailDAO;
	}

	public void setAssetStdProductEquipDetailDAO(AssetStdProductEquipDetailDAO assetStdProductEquipDetailDAO) {
		this.assetStdProductEquipDetailDAO = assetStdProductEquipDetailDAO;
	}

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetStdProductEquipListDAO.deleteList(id, user);
            }
        return result;
    }
	public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception
    {      
        return assetStdProductEquipListDAO.findTotalCount(assetStdProductCommonDTO,assetStdProductEquipListDTO,user);
    }
	public AssetStdProductEquipListDAO getAssetStdProductEquipListDAO() {
		return assetStdProductEquipListDAO;
	}

	public void setAssetStdProductEquipListDAO(AssetStdProductEquipListDAO assetStdProductEquipListDAO) {
		this.assetStdProductEquipListDAO = assetStdProductEquipListDAO;
	}
	
	public int insertList(AssetStdProductCommonDTO assetStdProductCommonDTO, AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO, AssetStdProductEquipListDTO assetStdProductEquipListDTO, User user) throws Exception 
	{
		int result = 0;
        
		String[] multiKey = assetStdProductEquipListDTO.getEquipId().split("\\+");
		
		assetStdProductEquipDetailDTO.setProductId(assetStdProductCommonDTO.getProductId());
		
        for(int i=0; i < multiKey.length; i++)
        {
        	assetStdProductEquipDetailDTO.setEquipId(multiKey[i]);
        	assetStdProductEquipDetailDTO.setAssetStdProductEquipId(assetStdProductEquipDetailDAO.getNextSequence("SQAPRDEQUIP_ID"));
        	
        	assetStdProductEquipDetailDAO.insertDetail(assetStdProductCommonDTO, assetStdProductEquipDetailDTO, user);
        }
		
		return result;
	}
}

