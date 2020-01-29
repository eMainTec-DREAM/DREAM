package gaia.gapg.service;

import java.util.List;

import gaia.gapg.dto.GaPgMngCommonDTO;


/**
 * 화면 - 목록 service
 * @author  kim21017
 * @version $Id: GaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface GaPgMngListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: GaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param gaPgMngCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPgList(GaPgMngCommonDTO gaPgMngCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: GaPgMngListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deletePage(String[] deleteRows) throws Exception;

}
