package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;

/**
 * 설비종류 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCatalogListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCatalogListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCatalogListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @return List
     */
    public List findEqCatalogList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, User loginUser);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCatalogListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEqCatalog(String id, String compNo);

}