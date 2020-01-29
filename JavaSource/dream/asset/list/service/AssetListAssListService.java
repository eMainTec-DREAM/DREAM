package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListAssListDTO;

/**
 * AssetListAss Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AssetListAssListService
{
    /**
     * FIND ASS ASSET LIST
     * @param assetListAssListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetListAssListDTO assetListAssListDTO, User user) throws Exception;
    /**
     * DELETE ASS ASSET LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetListAssListDTO
     * @return
     */
    public String findTotalCount(AssetListAssListDTO assetListAssListDTO, User user) throws Exception;
}
