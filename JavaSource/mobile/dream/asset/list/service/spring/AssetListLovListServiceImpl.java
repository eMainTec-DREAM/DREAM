package mobile.dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import mobile.dream.asset.list.dao.AssetListLovListDAO;
import mobile.dream.asset.list.dto.AssetListLovListDTO;
import mobile.dream.asset.list.service.AssetListLovListService;

/**
 * ¼³ºñÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="assetListLovListServiceTarget"
 * @spring.txbn id="assetListLovListService"
 * @spring.property name="assetListLovListDAO" ref="assetListLovListDAO"
 */
public class AssetListLovListServiceImpl implements AssetListLovListService
{
    /** ¼³ºñÆË¾÷ DAO */
    private AssetListLovListDAO assetListLovListDAO = null;

    public AssetListLovListDAO getAssetListLovListDAO() 
    {
		return assetListLovListDAO;
	}

	public void setAssetListLovListDAO(AssetListLovListDAO assetListLovListDAO) 
	{
		this.assetListLovListDAO = assetListLovListDAO;
	}

	public List findEquipList(AssetListLovListDTO assetListLovListDTO, User loginUser)
    {
        List resultList = null;
        resultList = assetListLovListDAO.findEquipList(assetListLovListDTO, loginUser);
        
        return resultList;
    }
}