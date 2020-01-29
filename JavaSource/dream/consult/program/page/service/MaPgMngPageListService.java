package dream.consult.program.page.service;

import java.util.List;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;

/**
 * 화면별 탭페이지  목록
 * @author  kim21017
 * @version $Id: MaPgMngPageListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngPageListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPgMngPageListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngPageListDTO
     * @throws Exception
     */
    public List findPageList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngPageListDTO maPgMngPageListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaPgMngPageListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deletePageList(String[] deleteRows) throws Exception;

}
