package dream.asset.categ.list.dao;


import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위 상세 dao
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqCtgAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbListDTO
     * @param user
     * @return
     */
    public MaEqCtgAsmbDetailDTO findDetail(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param user
     * @return
     */
    public int[] updateDetail(final List<MaEqCtgAsmbDetailDTO> list, final User user);
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user);
}