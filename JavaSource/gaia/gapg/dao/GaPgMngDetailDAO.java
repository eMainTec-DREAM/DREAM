package gaia.gapg.dao;

import common.bean.User;
import gaia.gapg.dto.GaPgMngDetailDTO;

/**
 * 화면 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: GaPgMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface GaPgMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public GaPgMngDetailDTO findDetail(String pageId, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     */
    public int insertDetail(GaPgMngDetailDTO gaPgMngDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     */
    public int updateDetail(GaPgMngDetailDTO gaPgMngDetailDTO);
}