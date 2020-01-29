package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.AssetListITSWListDAO;
import dream.asset.list.dto.AssetListITSWListDTO;
import dream.asset.list.service.AssetListITSWListService;

/**
 * List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="assetListITSWListServiceTarget"
 * @spring.txbn id="assetListITSWListService"
 * @spring.property name="assetListITSWListDAO" ref="assetListITSWListDAO"
 */
public class AssetListITSWListServiceImpl implements AssetListITSWListService
{
    private AssetListITSWListDAO assetListITSWListDAO = null;

    public List findList(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception
    {      
        return assetListITSWListDAO.findList(assetListITSWListDTO,user);
    }

    public int deleteList( String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + assetListITSWListDAO.deleteList(id, user);
            }
        return result;
    }

    public AssetListITSWListDAO getAssetListITSWListDAO() {
        return assetListITSWListDAO;
    }

    public void setAssetListITSWListDAO(AssetListITSWListDAO assetListITSWListDAO) {
        this.assetListITSWListDAO = assetListITSWListDAO;
    }    
    
    public String findTotalCount(AssetListITSWListDTO assetListITSWListDTO, User user)  throws Exception
    {
        return assetListITSWListDAO.findTotalCount(assetListITSWListDTO, user);
    }
}
