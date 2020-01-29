package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원 목록 dao
 * @author  syyang
 * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 */
public interface MaEqCtgSpecListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgSpecListDTO
     * @param loginUser
     * @return List
     */
    public List findSpecList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User loginUser);
    
    /**
     * delete
     * @author syyang
     * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSpecList(String id, String compNo);
    
    /**
     * FIND TOTAL LIST
     * @param maEqCatalogCommonDTO
     * @param maEqCtgSpecListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user) throws Exception;
    
}