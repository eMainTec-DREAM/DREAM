package dream.asset.categ.list.service;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위 상세
 * @author  kim210117
 * @version $Id: MaEqCtgAsmbDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqCtgAsmbDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaEqCtgAsmbDetailDTO findDetail(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user) throws Exception;
}
