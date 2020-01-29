package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;

/**
 * 설비종류 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqCatalogDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqCatalogDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCatalogDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqCatalogDetailDTO findDetail(String eqCtgId,User loginUser)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCatalogDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCatalogDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user) throws Exception;
    public int updateEqCtgFullDesc(User user) throws Exception;
}
