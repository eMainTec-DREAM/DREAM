package dream.asset.categ.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;

/**
 * 설비종류 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqCatalogDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaEqCatalogDetailDTO findDetail(String eqCtgId, User loginUser);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     */
    public int insertDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     */
    public int[] updateDetail(final List<MaEqCatalogDetailDTO> list, final User user);
}