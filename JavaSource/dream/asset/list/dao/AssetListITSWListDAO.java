package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListITSWListDTO;

/**
 * List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface AssetListITSWListDAO
{
    /**
     * FIND LIST
     * @param assetListITSWListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param assetListITSWListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception;
    
}
