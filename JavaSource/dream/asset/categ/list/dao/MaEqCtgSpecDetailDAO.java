package dream.asset.categ.list.dao;


import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원 상세 dao
 * @author  syyang
 * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 2015/12/04 08:10:27 syyang Exp $
 * @since   1.0
 */
public interface MaEqCtgSpecDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecListDTO
     * @param user
     * @return
     */
    public MaEqCtgSpecDetailDTO findDetail(MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user);
    /**
     * detail update
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, User user);
    
    /**
     * detail insert
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user);
    
}