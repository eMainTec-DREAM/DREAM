package gaia.gapg.service;

import common.bean.User;
import gaia.gapg.dto.GaPgMngDetailDTO;

/**
 * 화면 - 상세 service
 * 
 * @author kim21017
 * @version $Id: GaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface GaPgMngDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: GaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @return
     * @throws Exception
     */
    public GaPgMngDetailDTO findDetail(String pageId, User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: GaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(GaPgMngDetailDTO gaPgMngDetailDTO) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: GaPgMngDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(GaPgMngDetailDTO gaPgMngDetailDTO) throws Exception;
}
