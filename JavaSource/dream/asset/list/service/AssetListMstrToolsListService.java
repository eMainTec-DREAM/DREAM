package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListMstrToolsListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface AssetListMstrToolsListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: AssetListMstrToolsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param maEqMstrCommonDTO
     * @param assetListMstrToolsListDTO
     * @param loginUserr
     * @throws Exception
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUserr);

    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User user) throws Exception;

    public int[] deleteList(String[] deleteRows, User user) throws Exception;
}
