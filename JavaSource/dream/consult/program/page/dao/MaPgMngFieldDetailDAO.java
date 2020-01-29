package dream.consult.program.page.dao;

import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;

/**
 * 화면별 필드 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPgMngFieldDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgFieldId
     * @return
     */
    public MaPgMngFieldDetailDTO findDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngFieldDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngFieldDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO);
}