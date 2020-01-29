package dream.asset.categ.list.dao;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품 상세 dao
 * @author  kim21017
 * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgPartDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartListDTO
     * @param user
     * @return
     */
    public MaEqCtgPartDetailDTO findDetail(MaEqCtgPartListDTO maEqCtgPartListDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user);
    
    public String copyDetail(String oldEqCtgId, String newEqCtgId, String oldKeyId, String newKeyId, User user);

}