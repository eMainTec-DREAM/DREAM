package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 교정주기 목록 dao
 * @author  kim21017
 * @version $Id: AssetListTcycleListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface AssetListTcycleListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: AssetListTcycleListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param assetListTcycleListDTO
     * @param loginUser
     * @return List
     */
    public List findTcycleList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: AssetListTcycleListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteTcycleList(String id, String compNo);
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User user) throws Exception;

}