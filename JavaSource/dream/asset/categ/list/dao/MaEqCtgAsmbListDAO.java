package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgAsmbListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgAsmbListDTO
     * @param loginUser
     * @return List
     */
    public List findAsmbList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAsmbList(String id, String compNo);
    
    /**
     * FIND TOTAL LIST
     * @param maEqCatalogCommonDTO
     * @param maEqCtgAsmbListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user) throws Exception;
    
}