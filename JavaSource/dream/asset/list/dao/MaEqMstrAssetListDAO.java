package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrAssetListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return List
     */
    public List findAssetList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User loginUser );

    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAssetList(String id, String compNo);
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User user) throws Exception;

}