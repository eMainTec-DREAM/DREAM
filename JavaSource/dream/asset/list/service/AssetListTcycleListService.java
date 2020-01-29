package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 교정주기 목록
 * @author  kim21017
 * @version $Id: AssetListTcycleListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface AssetListTcycleListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: AssetListTcycleListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param assetListTcycleListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findTcycleList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: AssetListTcycleListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteTcycleList(String[] deleteRows, String compNo) throws Exception;

    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User user) throws Exception;

}
