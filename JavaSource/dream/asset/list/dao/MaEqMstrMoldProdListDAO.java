package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldProdListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldProdListDTO
     * @param loginUser
     * @return List
     */
    public List findMoldProdList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMoldProdList(String id, String compNo);
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User user) throws Exception;

}