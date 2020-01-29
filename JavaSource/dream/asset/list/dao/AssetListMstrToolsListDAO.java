package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListMstrToolsListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface AssetListMstrToolsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: AssetListMstrToolsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param maEqMstrCommonDTO
     * @param assetListMstrToolsListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser);

    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User user) throws Exception;

    public int[] deleteList(final List<AssetListMstrToolsListDTO> list, final User user) throws Exception;
}