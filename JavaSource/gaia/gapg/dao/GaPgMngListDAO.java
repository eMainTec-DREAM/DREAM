package gaia.gapg.dao;

import java.util.List;

import gaia.gapg.dto.GaPgMngCommonDTO;

/**
 * 화면 - 목록 dao
 * @author  kim21017
 * @version $Id: GaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface GaPgMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: GaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngCommonDTO
     * @return List
     */
    public List findPgList(GaPgMngCommonDTO gaPgMngCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: GaPgMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDTOList
     * @return
     */
    public int deletePage(String id);
}