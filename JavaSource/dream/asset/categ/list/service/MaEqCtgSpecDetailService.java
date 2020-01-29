package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원 상세
 * @author  syyang
 * @version $Id: MaEqCtgSpecDetailService.java,v 1.0 2015/12/04 09:08:29 syyang Exp $
 * @since   1.0
 */
public interface MaEqCtgSpecDetailService
{    
    /**
     * detail find
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaEqCtgSpecDetailDTO findDetail(MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user)throws Exception;
    /**
     * detail update
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception;
}
