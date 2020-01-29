package dream.consult.program.page.dao;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;

/**
 * 화면별 탭페이지 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngPageDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgPageId
     * @return
     */
    public MaPgMngPageDetailDTO findDetail(String pageId, String pgPageId, String lang);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
}