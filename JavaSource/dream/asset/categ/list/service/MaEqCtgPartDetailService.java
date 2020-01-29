package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품 상세
 * @author  kim210117
 * @version $Id: MaEqCtgPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqCtgPartDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaEqCtgPartDetailDTO findDetail(MaEqCtgPartListDTO maEqCtgPartListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgPartDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgPartDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception;
    
    public String copyDetail(String oldEqCtgId, String newEqCtgId, String oldKeyId, String newKeyId, User user) throws Exception;
}
