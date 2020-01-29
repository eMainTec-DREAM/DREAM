package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListAssListDTO;

/**
 * AssetListAss Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetListAssListDAO
{
    /**
     * FIND LIST
     * @param assetListAssListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(AssetListAssListDTO assetListAssListDTO, User user) throws Exception;
    /**
     * CHECK DELETE ROW
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int chkDelRow(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param assetListAssListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetListAssListDTO assetListAssListDTO, User user) throws Exception;
    
}
