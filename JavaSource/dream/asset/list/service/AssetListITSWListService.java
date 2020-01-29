package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListITSWListDTO;

/**
 * List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface AssetListITSWListService
{
    /**
     * FIND ASS ASSET SCORE LIST
     * @param assetListITSWListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception;
    /**
     * DELETE ASS ASSET SCORE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetListITSWListDTO
     * @return
     */
    public String findTotalCount(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception;
}
