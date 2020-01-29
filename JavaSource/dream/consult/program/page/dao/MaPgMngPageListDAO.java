package dream.consult.program.page.dao;

import java.util.List;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;

/**
 * 화면별 탭페이지 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngPageListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngPageListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngPageListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngPageListDTO
     * @return List
     */
    public List findPageList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngPageListDTO maPgMngPageListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngPageListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deletePageList(String id);
}